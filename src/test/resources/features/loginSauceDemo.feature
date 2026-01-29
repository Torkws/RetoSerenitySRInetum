Feature: Funcionalidad de Login para E-commerce de Sauce Demo

  Background:
    Given que el usuario navega a la página de inicio de sesión de Sauce Demo

    @saucedemo @login @E01 @happyPath
  Scenario Outline: Inicio de sesión exitoso con credenciales válidas
    When el usuario inicia sesión con el usuario <user> y contraseña <pass>
    Then el usuario debería ser redirigido a la página de productos
    And el inventario de productos debería ser visible
    Examples:
      | user                    | pass         |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |


    @saucedemo @login @E02 @unhappyPath
  Scenario Outline: Validar mensaje de error al intentar iniciar sesión con usuario bloqueado o credenciales inválidas
    When el usuario inicia sesión con el usuario <user> y contraseña <pass>
    Then se debería mostrar un mensaje de error de login <messageError>
    Examples:
      | user            | pass         | messageError                                                              |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | xxxx         | Epic sadface: Username and password do not match any user in this service |
      | standard_user   |              | Epic sadface: Password is required                                                                          |
      |                 | secret_sauce | Epic sadface: Username is required                                                                          |