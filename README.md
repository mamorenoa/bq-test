# bq-test

La documentación de la prueba consta, en primer lugar, de una pequeña introducción de la arquitectura y organización de la aplicación. Posteriormente, se realiza una descripción de cada caso de uso de la prueba que se corresponde con cada rama feature que se ha ido generando en git mediante el uso de gitflow. 

## Aspectos generales
La aplicación está dividida en paquetes que hacen referencia a los tipos de componentes (activities, fragments, adapters, etc) que se usan. Se ha decidido usar dos tipos de product flavors (sandbox y production) para cargar la configuración correspondiente al entorno de sandbox de Evernote o la configuración del entorno de producción. Se ha optado por usar una navegación basada en Fragments, donde las Activities sólo sirven como medio de comunicación entre Fragments o para decidir qué vista Fragment cargar en cada caso. La aplicación consta de un Singleton donde se almacenan como instancias únicas algunos datos como la sesión del usuario. Se ha intentado abstraer y proveer toda la funcionalidad que se pide de Evernote en una clase EvernoteHelper. Las llamadas asíncronas se han realizado mediante AsyncTasks y se ha implementado un listener genérico para servir los datos resultantes en cada operación y manejar errores. 

## Login

## Lista de notas

### Ordenar notas

## Detalle de nota
