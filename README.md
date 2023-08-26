<p align="center">
  <img src="./icon.png" align="center" width="125">
</p>
<div align="center">
  <h2>S-Manga</h2>
  <p>S-Manga es una aplicación web desarrollada en Spring y utiliza MySQL como base de datos. Permite a los usuarios alquilar y disfrutar de una amplia variedad de mangas japoneses.</p>
</div>

## Acceso inicial:

| Usuario | Contraseña |
|---------|------------|
| admin   | 123        |
| neko    | 123        |

Niveles de Acceso:

- **Admin**: El usuario "admin" tiene acceso total a todas las funcionalidades de la aplicación. Puede ver, crear, editar y eliminar datos en todas las páginas y secciones.

- **Neko**: El usuario "neko" tiene acceso únicamente a ciertas páginas y funcionalidades específicas de la aplicación. Su capacidad de navegación puede estar restringida en algunas áreas.

## Herramientas
- Spring Tool Suite 4
- MySQL Workbench 8.0 o superior

## Tecnologías
- HTML, CSS, JS, JQUERY, JAVA, MVC
- Bootstrap5
- Alertify
- Datatables
- Thymeleaf
- JPA
- MySql
- Java 17

## Instalación y ejecución
1. Clonar el repositorio `https://github.com/NeiDenn/spring-smanga.git`
2. Importar el proyecto en el IDE Spring Tool Suite 4 
3. Ajustar en el archivo `application.properties` la cadena de conexión `BD_SMANGA` a la base de datos que se encuentra en la carpeta `database`
```
server.port=8091
spring.jpa.database=MYSQL
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/BD_SMANGA?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=mysql
```
5. Ejecutar

## Screenshots

![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/b2b5aae7-f8fc-4ffd-956e-2b40cf294301)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/e36171cf-02b9-4f8d-aad7-e2ad346edfd1)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/29ae1aba-5886-4bec-9489-b63142ea7b0e)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/2972f297-fc0f-4c02-906f-cc206bb32aab)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/bcb8b2f1-a002-40b9-809d-7b1ad8ca932f)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/0497d2fd-acea-4395-8e93-a69cf8e384ac)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/6d567cb4-8af3-4724-8972-4a74e3b508bc)
![image](https://github.com/NeiDenn/spring-smanga/assets/85379478/4fd384fd-ba87-4844-8926-645a26847950)


