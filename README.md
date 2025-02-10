# Proyecto Irrgarten

El proyecto **Irrgarten** consiste en el desarrollo de un juego de laberinto implementado en los lenguajes de programación orientados a objetos **Java** y **Ruby**. A través de cinco prácticas, se exploran diferentes conceptos del diseño y la programación orientada a objetos (POO).

## Contenido del Proyecto

### Práctica 1: Ejercicios Básicos de Programación Orientada a Objetos
- **Objetivo:** Familiarizarse con los conceptos básicos de POO mediante la implementación inicial de clases y tipos enumerados para el juego.
- **Trabajo realizado:**
  - Implementación de las clases `Weapon`, `Shield`, `Dice` y `GameState`.
  - Creación de métodos básicos como ataques, defensas y manejo de elementos del juego.
  - Uso de enumerados para representar direcciones, orientaciones y personajes del juego.
  - Diseño de un programa principal para probar las clases desarrolladas en ambas plataformas.

### Práctica 2: Implementación de la Estructura de Clases
- **Objetivo:** Desarrollar la estructura de clases completa del juego siguiendo un diseño orientado a objetos.
- **Trabajo realizado:**
  - Implementación del diagrama de clases completo, incluyendo las clases `Monster`, `Player`, `Labyrinth` y `Game`.
  - Definición de atributos y cabeceras de métodos para todas las clases.
  - Desarrollo inicial de métodos funcionales para interacción básica entre los elementos del juego.
  - Configuración inicial del laberinto y los objetos dentro de él.

### Práctica 3: Implementación de la Funcionalidad del Sistema
- **Objetivo:** Finalizar la implementación de la primera versión funcional del juego, añadiendo los métodos faltantes y una interfaz textual para jugar.
- **Trabajo realizado:**
  - Implementación de los métodos faltantes según los diagramas de secuencia proporcionados.
  - Desarrollo de la clase `TextUI` como vista para mostrar el estado del juego y recibir comandos del jugador.
  - Creación de la clase `Controller` para coordinar la interacción entre la vista y el modelo del juego.
  - Integración de las clases en un programa principal que permite jugar partidas desde la consola.
  - Depuración y prueba de la funcionalidad del juego mediante programas de prueba para localizar y corregir errores.

### Práctica 4: Ampliación del Sistema con Herencia y Polimorfismo
- **Objetivo:** Introducir herencia y polimorfismo en el diseño del juego, mejorando su estructura y añadiendo nuevas funcionalidades.
- **Trabajo realizado:**
  - Creación de las clases abstractas `CombatElement` y `LabyrinthCharacter` para generalizar aspectos comunes de armas, escudos, jugadores y monstruos.
  - Implementación de la clase `FuzzyPlayer`, un nuevo tipo de jugador que utiliza el azar en sus acciones y que se genera al resucitar un jugador estándar.
  - Modificación de la clase `Dice` con un nuevo método para calcular direcciones de movimiento basadas en inteligencia y azar.
  - En Java, desarrollo de las barajas de cartas `CardDeck` para gestionar armas y escudos como elementos obtenidos al ganar combates.
  - Ajustes en las clases existentes para integrar las nuevas funcionalidades y conceptos de herencia.
  - Pruebas y depuración para garantizar el correcto funcionamiento del sistema ampliado.

### Práctica 5: Implementación de una Interfaz Gráfica de Usuario
- **Objetivo:** Añadir una interfaz gráfica de usuario (GUI) al juego siguiendo el patrón Modelo-Vista-Controlador (MVC).
- **Trabajo realizado:**
  - Creación de una interfaz común `UI` para unificar las interfaces textual y gráfica del juego.
  - Desarrollo de una clase que hereda de `JFrame` como ventana principal del juego, mostrando el estado del laberinto, jugadores, monstruos y eventos en elementos gráficos como `JTextArea` y `JLabel`.
  - Implementación de la clase `Cursors`, un cuadro de diálogo basado en `JDialog` que permite al jugador seleccionar direcciones de movimiento mediante botones.
  - Integración de la interfaz gráfica con el controlador existente, manteniendo el patrón MVC.
  - Actualización del método `nextMove` para delegar la entrada del usuario a la nueva clase `Cursors`.
  - Pruebas del sistema para asegurar la comunicación fluida entre el modelo, la vista gráfica y el controlador.
