# Desafio WalmartDigital

Readme con los comentarios del desafio de WalmartDigital.

## Data Model 🚀

Se agrega modelo de base de datos en los formatos .PNG al igual que el proyecto original creado con drawio. Se utiliza esta aplicación ya que nos permite crear modelos de base de datos de manera mas sencilla (https://app.diagrams.net/).

### Deployment 🔧

Creacion de la imagen de docker. Desde la raíz del proyecto se debe ejecutar la siguiente linea de comando ya que el proyecto cuenta con un DockerFile. Es necesario tener instalado Docker en el computador.

```
docker build -t "spring-etl-docker" .
```
Luego de la creación de la imagen de docker es necesario ejecutar la siguiente instrucción para el despliegue de la aplicación.

Ejecución del container para versiones unix.

```
docker run -v $(pwd)/data:/data spring-etl-docker
```
Ejecución del container para versiones windows.

```
docker run -v path_raiz_proyecto/data:/data spring-etl-docker
```

Para la correcta ejecución y generación de los resultados, es necesario notificar el path raíz del proyecto. Para versiones unix basta con estar en la raíz del proyecto y el comando $(pwd) entregara la ruta correspondiente, para casos de windows es necesario notificar la ruta completa de la raíz del proyecto.


## Construido con 🛠️

* [eclipse](https://www.eclipse.org/downloads/) - Ide de desarrollo.
* [Maven](https://maven.apache.org/) - Manejador de dependencias.
* [drawio](https://app.diagrams.net/) - Usado para generar modelo de base de datos.
* [Java Spring](https://spring.io/) - Framework para el desarrolo de la aplicación.
* [Docker](https://www.docker.com/) - Para el despliegue de aplicaciones dentro de contenedores.
* [openjdk](https://hub.docker.com/_/openjdk) - Imagen oficial para la ejecución de aplicaciones java a través de docker.


