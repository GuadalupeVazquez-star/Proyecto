# Proyecto final de la materia Topicos Avanzados de Programacion y Simulacion
Esta diseñada para profundizar el desarrollo de mas practicas e interactuar con nuevos problemas para la programacion.
## Tabla de contenido
- Codigo Practica
- SistemaInventarioSimulacion
## Descripcion del Proyecto
El proyecto cuenta con un simulador de inventario que contiene su base de datos, sus clases y atributos.
Esta diseñada para:
- Simular la demanda y entrega de productos de un almacén.
- Calcula automáticamente cuando hacer pedidos usando punto de reorden.
- Visualiza en tiempo real el nivel de stock con graficas.
- Persiste los datos en base de datos para no perder información al cerrar.
## Fases:
En Java con id ApacheNetbeans esta construida por fases:
- Fase 0: Arquitectura del proyecto MVC(Modelo Vista Controlador) + DAO(Objeto de Acceso de Datos).
- Fase 1 Paso 1: El Modelo de datos de la clase Producto.java
- Fase 1 Paso 2: La Persistencia con la Clase ProductoDAO.java
- Fase 1 Paso 3: Prueba de BD TestDriver. Y TestBD java
- Fase 2 Paso 1: Eventos con Evento.java
- Fase 2 Paso 2: Demanda aleatoria GeneradorDemanda.java
- Fase 2 Paso 3: Motor de Simulación MotorSimulacion.java
- Fase 3 Paso 1: Interfaz Swing .java
- Fase 3 Paso 2: Tabla + Grafica .java
- Fase 4 Paso 1: Migracion a MYSQL .java
- Fase 4 Paso 2: Actualización de las clases .java
## Tecnologías usadas:
- Lenguaje base: JAVA id NETBEANS
- Interfaz: Java Swing
- Librerias: JFreechart, jcommon 
- Base de datos: SQLLite, MySQL, JDBC
- Control de repositorio: GITHUB
## Estructura del Proyecto en Java:
SistemaInventarioSimulacion[Main]
- Source Packages (paquetes)
 - dao
   - ProductoDAO.java (clase)
 - modelo
   - Producto.java (clase)
 - simulacion
   - Evento.java (clase)
   - GeneradorDemanda.java (clase)
   - MotorSimulacion.java (clase)
 - vista
   - InventarioTableModel.java (clase)
   - PanelGrafica.java (clase)
   - VentanaPrincipal (clase JFrame)
- Test Packages (paquetes de prueba)
- Libraries (librerias)
  - jfreechart-1.5.3.jar
  - jcommon-1.0.24.jar
  - mysql-connector-j-9.7.0.jar
  - JDK 17 (Default)
- Test Libraries
## Estructura del proyecto en GitHub:
- Proyecto
 - CodigoPractica
   - SistemaInventarioSimulacion
     - scr (scr)
       - (paquetes con cada código)
## Instrucciones para descargar el codigo:
- Tener instalado Java con el id Apache Netbeans
- Instalar MySQL para la base de datos
- Crear la carpeta principal en Netbeans con el nombre de SistemaInventarioSimulacion
- Realizar las clases en dichos paquetes de java (en Netbeans)
- Copiar y pegar los códigos del repositorio de github que dichos códigos vienen dentro de la estructura del proyecto en git
- Verificar que la estructura este bien elaborada y quede como en "Estructura del proyecto en Java"




