# Suite de Pruebas Automatizadas - Sauce Demo con Serenity BDD

##  Descripción General

Suite de pruebas automatizadas para la aplicación web **Sauce Demo**, desarrollada con **Java 17**, **Selenium WebDriver**, y **Serenity BDD**. El proyecto implementa el patrón de diseño **Screenplay** siguiendo principios de Clean Code y SOLID para demostrar habilidades avanzadas en arquitectura de software orientada a pruebas.

### Características Principales
- ✅ Patrón Screenplay implementado correctamente
- ✅ Separación de responsabilidades (Actors, Abilities, Tasks, Interactions, Questions)
- ✅ Manejo inteligente de estados con `actor.remember()` y `actor.recall()`
- ✅ Esperas explícitas con timeouts configurables
- ✅ Selectores robustos (CSS Selectors y XPaths relativos)
- ✅ Reportabilidad en vivo con anotaciones `@Step`
- ✅ Integración con Cucumber BDD
- ✅ Reportes visuales automáticos de Serenity

---

##  Arquitectura Implementada

### Estructura del Proyecto

```
src/test/java/inetum/
├── abilities/                 # Capacidades del Actor (ej: BrowseTheWeb)
├── tasks/                     # Tareas de alto nivel (ej: IniciarSesionTask)
├── interactions/              # Acciones atómicas (ej: ClickInteraction, InputInteraction)
├── questions/                 # Consultas para validaciones (ej: CommonQuestions)
├── ui/                        # Definición de elementos (Page Objects)
├── models/                    # Modelos de datos (ej: CarritoInfo)
├── stepdefinitions/           # Steps Gherkin (Cucumber)
├── runners/                   # Ejecutores de pruebas
├── actors/                    # Definición de actores
└── utils/                     # Utilidades (Credentials, CommonUtils)
```

### Componentes Clave del Patrón Screenplay

#### 1. **Abilities** (Capacidades)
```java
BrowseTheWeb extends Ability
└─ Proporciona acceso al WebDriver para interactuar con la página
```
El Actor necesita la capacidad de navegar por la web, proporcionada por `BrowseTheWeb.with(driver)`.

#### 2. **Tasks** (Tareas)
Encapsulan flujos de negocios complejos:
- `IniciarSesionTask` - Realiza login con usuario y contraseña
- `ObtieneProductosByFilter` - Obtiene productos aplicando filtros y guarda en memoria
- `AgregaProductosTask` - Agrega productos al carrito
- `VisualizaProductosEnCarritoTask` - Verifica productos en el carrito
- `RealizaCheckoutTask` - Completa formulario de checkout
- `ConfirmacionCompraTask` - Confirma la compra
- `ValidarConfirmacionCompraTask` - Valida mensaje de confirmación

#### 3. **Interactions** (Interacciones)
Acciones atómicas y reutilizables:
- `ClickInteraction` - Hace clic en un elemento
- `InputInteraction` - Ingresa valores en campos
- `WaitInteraction` - Esperas explícitas (VISIBLE, CLICKABLE, PRESENT)
- `NavigationInteraction` - Navegación
- `ScrollInteraction` - Scroll en la página
- `KeyInteraction` - Acciones de teclado

#### 4. **Questions** (Preguntas)
Obtienen información para validaciones:
- `CommonQuestions.checkIfObjectIsDisplayed()` - Verifica visibilidad
- `CommonQuestions.getObjectText()` - Obtiene texto de elemento
- `CommonQuestions.getProductsNameAndPrice()` - Extrae datos de productos

#### 5. **UI Models** (Page Objects)
Centralizan selectores en clases específicas:
- `LoginPage` - Elementos de login
- `InventoryPage` - Elementos del inventario y métodos dinámicos
- `CheckoutPage` - Elementos del checkout
- `CommonPage` - Elementos comunes

---

## 🛠️ Configuración del Proyecto

### Requisitos
- **Java 17+** | **Maven 3.8.1+** | **Chrome/Edge**

### Dependencias
```xml
<serenity.version>4.1.3</serenity.version>
```
- serenity-core, serenity-cucumber, serenity-screenplay
- junit, hamcrest, javafaker, lombok

### Serenity Properties
```ini
webdriver.driver=chrome
serenity.take.screenshots=FOR_EACH_ACTION
serenity.verbose.screenshots=true
```
Cambiar driver: `webdriver.driver=edge` o `webdriver.driver=firefox`

---

## 🚀 Ejecución de Pruebas

```bash
# Todas las pruebas
mvn clean verify

# Por tag
mvn clean verify -Dcucumber.filter.tags="@login"
mvn clean verify -Dcucumber.filter.tags="@E03"

# Comando completo con propiedades
mvn clean verify -Dwebdriver.driver=chrome -Denvironment=uat -Dcucumber.filter.tags=@saucedemo

# Por feature
mvn clean verify -Dfeatures="src/test/resources/features/loginSauceDemo.feature"

# Con navegador visible
mvn clean verify -Dserenity.headless=false

# Generar reporte
mvn serenity:aggregate
```

Reportes: `target/site/serenity/index.html`

### Drivers Disponibles

| Driver | Comando | Descripción |
|--------|---------|-------------|
| **Chrome** | `-Dwebdriver.driver=chrome` | Driver por defecto. Navegador de alto rendimiento |
| **Edge** | `-Dwebdriver.driver=edge` | Driver Microsoft Edge. Compatible con Chromium |
| **Firefox** | `-Dwebdriver.driver=firefox` | Driver Mozilla Firefox. Alternativa open-source |

**Parámetros Comunes:**
- `-Dwebdriver.driver=chrome` → Define el navegador a usar
- `-Denvironment=uat` → Define el ambiente (uat, dev, prod, etc.)
- `-Dcucumber.filter.tags=@saucedemo` → Filtra scenarios por tag

### Ejecución por Driver

```bash
# Ejecutar con Chrome
mvn clean verify -Dwebdriver.driver=chrome -Dcucumber.filter.tags=@saucedemo

# Ejecutar con Edge
mvn clean verify -Dwebdriver.driver=edge -Dcucumber.filter.tags=@saucedemo

# Ejecutar con Firefox
mvn clean verify -Dwebdriver.driver=firefox -Dcucumber.filter.tags=@saucedemo

# Ejecutar solo login con Chrome
mvn clean verify -Dwebdriver.driver=chrome -Dcucumber.filter.tags=@login

# Ejecutar E03 con Edge en modo visible
mvn clean verify -Dwebdriver.driver=edge -Dcucumber.filter.tags=@E03 -Dserenity.headless=false
```

---

## 📊 Escenarios de Prueba

### Feature 1: Login (E01, E02)
- **E01:** Login exitoso (standard_user, problem_user, performance_glitch_user)
- **E02:** Validar errores (usuario bloqueado, credenciales incorrectas, campos vacíos)

### Feature 2: Compra de Productos (E03)
1. Navega a login
2. Inicia sesión con standard_user
3. Obtiene N productos con filtro (PRECIOMAYOR | PRECIOMENOR)
4. Agrega productos al carrito
5. Valida productos en carrito
6. Completa checkout con datos aleatorios
7. Confirma compra
8. Valida mensaje "Thank you for your order!"

---


## ✨ Patrones Clave Implementados

### 1. Separación de Responsabilidades
- **Page Objects** → Selectores sin lógica
- **Interactions** → Acciones atómicas
- **Tasks** → Flujos de negocio
- **Questions** → Validaciones y consultas

### 2. Manejo de Estado (actor.remember/recall)
```java
actor.remember(CARRITO_INFO_KEY, carritoInfo);
List<CarritoInfo.ProductoCarrito> recuperados = actor.recall(PRODUCTOS_KEY);
```

### 3. Esperas Explícitas Inteligentes
```java
WaitInteraction.forElementToBeVisible(target)
WaitInteraction.forElementToBeClickable(target)
WaitInteraction.forElementToBePresent(target)
```
**Sin Thread.sleep()** - Garantiza resiliencia

### 4. Selectores Robustos
```java
Target.located(By.cssSelector("div[data-test='inventory-item']"))
locatedBy("//div[@data-test='inventory-item']//div[@data-test='inventory-item-name']")
getProductAddButton(String productName)  // Dinámicos
```

### 5. Documentación Viva
```java
@Step("{0} inicia sesión correctamente")
@Step("{0} obtiene {cantidad} productos con filtro {filtro}")
```

### 6. Generación de Datos
```java
CommonUtils.GenerarDataMock("firstName")   // JavaFaker
CommonUtils.GenerarDataMock("lastName")
CommonUtils.GenerarDataMock("postalCode")
```


---

**Autor:** Steven Navarrete  
**Última actualización:** 30 de enero de 2026
