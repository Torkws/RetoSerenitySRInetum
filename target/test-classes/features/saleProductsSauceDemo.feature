Feature: Funcionalidad de compra y gestión del carrito de productos en Sauce Demo

  Background:
    Given que el usuario navega a la página de inicio de sesión de Sauce Demo

  @saucedemo @E2E @E03 @happyPath
  Scenario Outline: Realiza compra exitosa de productos en SauceDemo
    When el usuario inicia sesión con el usuario standard_user
    And agrega <cantidad> productos con el filtro <filter> al carrito
    And se visualiza los productos en el carrito de compras
    And se completa el proceso de llenado de formulario con los inputs <firstName>, <lastName> y <postalCode>
    When se procesa la confirmación de la compra
    Then debería visualizar la confirmación de la compra
    And debería ver el mensaje de compra exitosa Thank you for your order!
    Examples:
      | firstName | lastName | postalCode | cantidad | filter      |
      | random    | random   | random     | 2        | PRECIOMAYOR |
      | random    | random   | random     | 3        | PRECIOMENOR |

