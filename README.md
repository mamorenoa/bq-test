# bq-test

La documentación de la prueba consta, en primer lugar, de una pequeña introducción de la arquitectura y organización de la aplicación. Posteriormente, se realiza una descripción de cada caso de uso de la prueba que se corresponde con cada rama feature que se ha ido generando en git mediante el uso de gitflow. 

## Aspectos generales
- La aplicación está dividida en paquetes que hacen referencia a los tipos de componentes (activities, fragments, adapters, etc) que se usan. 
- Se ha optado por usar una navegación basada en Fragments, donde las Activities sólo sirven como medio de comunicación entre Fragments o para decidir qué vista Fragment cargar en cada caso. 
- La aplicación consta de un singleton donde se almacenan como instancias únicas algunos datos como la sesión del usuario. Se ha intentado abstraer y proveer toda la funcionalidad que se pide de Evernote en una clase EvernoteHelper. Las operaciones que se han abstraido son loginUser, getUserInfo, getUserNotebooks, getUserNotes y createNote.
- Las llamadas asíncronas se han realizado mediante AsyncTasks y se ha implementado un listener genérico para servir los datos resultantes en cada operación y manejar posibles errores. 
- Se ha usado la librería ButterKniffe para facilitar la carga de Views en Fragments y Activities. 
- La aplicación sólo funciona en el entorno de Sandbox de Evernote. 

## Login
Esta funcionalidad se ha desarrollado en la rama feature/userLogin. La primera vez que el usuario accede a la aplicación la activity HomeActivity comprueba si se ha logado anteriormente. En el caso de que no haya hecho login, se muestra al usuario la pantalla de acceso al login con Evernote (LoginFragment), en caso contrario, se le muestra la pantalla con la lista de sus notas personales (NotesFragment). Una vez se ha finalizado esta feature, se ha mergeado con la rama develop y ha sido eliminada. 

## Lista de notas
Esta funcionalidad se ha desarrollado en la rama feature/getNotes. Se ha decidido mostrar las notas mediante una vista RecyclerView cuyos items son del tipo CardView los cuales se encargan de mostrar la información básica de las notas (título y fecha de creación). Al inciar este Fragment (NotesFragment) se pide a Evernote el nombre del usuario logado y se muestra en una vista SnackBar. Posteriomente, se pide a Evernote la lista de Notebooks (getUserNotebooks) del usuario, se recorre dicha lista y se piden todas las notas de cada Notebook (getUserNotes). Todas las llamadas a Evernote son asíncronas, sólo después obtener el resultado de la llamada getUserNotebooks, se realiza la llamada getUserNotes. Las llamadas se sincronizan mediante el uso del listener IEvernoteHelperResultListener. Se ha implementado un mecanismo pull-to-refresh para la lista de notas a través de la vista SwipeRefreshLayout. Una vez se ha finalizado esta feature, se ha mergeado con la rama develop y ha sido eliminada. 

### Ordenar notas
Se ha incluido un menu desplegable mediante la vista FloatingActionsMenu. Dicha vista tiene tres vistas hijas del tipo FloatingActionButton encargadas de ordenar la lista, por criterio de fecha y título, y de crear nuevas notas. El orden de la lista se ha realizado mediante clases Comparator (ComparatorNotesTitle y ComparatorNotesDate). Esta funcionalidad está incluida en la rama feature/getNotes.

## Detalle de nota
Esta funcionalidad se ha desarrollado en la rama feature/detailNote. Al pulsar en cada nota de la lista se muestra un fragment (DetailNoteFragment) con el contenido de la misma (título, fecha de creación y contenido). La clase RecyclerView no cuenta con un listener de clicks por defecto, se ha implementado uno propio (OnItemRecyclerClickListener). El contenido de la nota se muestra en una vista TextView como HTML. Una vez se ha finalizado esta feature, se ha mergeado con la rama develop y ha sido eliminada.

## Crear nota
Esta funcionalidad se ha desarrollado en la rama feature/createNotes. El usuario puede crear una nota introduciendo únicamente un título y un contenido de la misma. Una vez introducida la nota, el fragment de creación de notas (CreateNotesFragment) avisa al fragment listado (NotesFragment) para que actualice su contenido mediante el listener IRefreshFragmentData. 

## OCR
