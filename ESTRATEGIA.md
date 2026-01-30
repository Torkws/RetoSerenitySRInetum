# Informe de Estrategia - Suite de Pruebas Sauce Demo

**Autor:** Steven Navarrete  
**Fecha:** 30 de enero de 2026

---

## 1. Estrategia de Manejo de Estados

### Problema
En flujos E2E (login → productos → validar precios → checkout), es crítico mantener consistencia de información entre pasos sin acoplar Tasks.

### Solución: Actor.remember/recall

```java
// Guardar estado
CarritoInfo carritoInfo = new CarritoInfo(productosSeleccionados);
actor.remember("carritoInfo", carritoInfo);
actor.remember("productos", productosSeleccionados);

// Recuperar estado
List<CarritoInfo.ProductoCarrito> recuperados = 
    (List<CarritoInfo.ProductoCarrito>) actor.recall("productos");
```

**Ventajas:**
- ✅ Información en contexto del Actor (no variables globales)
- ✅ Desacoplamiento entre Tasks
- ✅ Facilita pruebas paralelas
- ✅ Validación de consistencia de precios entre selección y carrito


---

## 2. Estrategia de Selección Dinámica

### Problema
Selectores frágiles: productos dinámicos, IDs basados en nombres, múltiples elementos con estructura similar.

### Soluciones Implementadas

#### A) IDs Dinámicos
```java
public static Target getProductAddButton(String productName) {
    return Target.the("Botón: " + productName)
        .located(By.id("add-to-cart-" + productName.toLowerCase().replace(" ", "-")));
}
// "Sauce Labs Backpack" → id="add-to-cart-sauce-labs-backpack"
```

#### B) Atributos data-test
```java
Target.the("items")
    .located(By.cssSelector("div[data-test='inventory-item']"));
```

#### C) XPath Relativo Indexado
```java
locatedBy("(//div[@data-test='inventory-item'])[" + (index + 1) + "]"
    + "//div[@data-test='inventory-item-name']");
```

#### D) XPath con Normalización
```java
locatedBy("//div[@data-test='inventory-item-name' " +
    "and normalize-space(text())='{0}']".replace("{0}", productName));
```

### Extracción Dinámica de Productos

```java
public static Question<List<CarritoInfo.ProductoCarrito>> getProductsNameAndPrice(Target target) {
    return Question.about("Obtener productos")
        .answeredBy(actor -> {
            List<CarritoInfo.ProductoCarrito> productos = new ArrayList<>();
            int index = 0;
            boolean hasMore = true;
            
            while (hasMore) {
                try {
                    String name = the(InventoryPage.getProductNameByIndex(index))
                        .answeredBy(actor).getText();
                    String priceText = the(InventoryPage.getProductPriceByIndex(index))
                        .answeredBy(actor).getText();
                    double price = Double.parseDouble(priceText.replace("$", "").trim());
                    
                    productos.add(new CarritoInfo.ProductoCarrito(name, price));
                    index++;
                } catch (Exception e) {
                    hasMore = false;
                }
            }
            return productos;
        });
}
```

### Aplicación de Filtros

```java
// Ordenamiento dinámico según parámetro
switch (filtro.toUpperCase()) {
    case "PRECIOMAYOR":
        productosCarrito.sort(
            Comparator.comparingDouble(CarritoInfo.ProductoCarrito::getPrecio).reversed());
        break;
    case "PRECIOMENOR":
        productosCarrito.sort(
            Comparator.comparingDouble(CarritoInfo.ProductoCarrito::getPrecio));
        break;
}

// Seleccionar primeros N
List<CarritoInfo.ProductoCarrito> seleccionados = productosCarrito
    .stream().limit(cantidad).collect(Collectors.toList());

actor.remember("productos", seleccionados);
```

**Flujo:**
```
Input: cantidad=2, filtro=PRECIOMAYOR
  ↓
Extrae todos: [10, 50, 30, 20]
  ↓
Ordena descendente: [50, 30, 20, 10]
  ↓
Toma primeros 2: [50, 30]
  ↓
Guarda en Actor → Usa en AgregaProductosTask
```

---

## 3. Validación de Inconsistencias

```java
// Si precioGuardado ≠ precioCarrito → Falla inmediata
actor.attemptsTo(
    Ensure.that(precioGuardado == precioCarrito).isTrue()
);
```

Ejemplo: Si producto cambió de $29.99 a $35.99 → **Test Falla**

---

## 4. Esperas Explícitas Inteligentes

```java
WaitInteraction.forElementToBeVisible(target)      // 10s timeout
WaitInteraction.forElementToBeClickable(target)
WaitInteraction.forElementToBePresent(target)
```

**Sin Thread.sleep()** → Ejecución eficiente sin delays artificiales

---

## 5. Comparación: Frágil vs Robusta

### ❌ Evitado
```java
Target button = Target.the("Button").located(By.xpath("//button[1]"));
Thread.sleep(2000);  // ANTI-PATRÓN
// ... sin validación de datos ...
```

### ✅ Implementado
```java
Target button = Target.the("Botón: " + productName)
    .located(By.id("add-to-cart-" + productName.toLowerCase().replace(" ", "-")));

actor.attemptsTo(WaitInteraction.forElementToBeClickable(button));
actor.remember("productos", seleccionados);
actor.attemptsTo(Ensure.that(precioGuardado == precioCarrito).isTrue());
```

---

**Versión:** 1.0  
**Autor:** Steven Navarrete  
**Fecha:** 30 de enero de 2026
