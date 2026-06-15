# MiTienda - Sistema de Ventas

## Descripción
MiTienda es un sistema de ventas de escritorio desarrollado con JavaFX y Maven.
Permite gestionar productos mediante un CRUD completo, con autenticación de usuarios por rol.

## Captura del Dashboard
> *(Agrega aquí una captura de pantalla del dashboard una vez ejecutado el proyecto)*

## Tecnologías Utilizadas
- Java 17
- JavaFX 21
- CSS (estilos inline con JavaFX)
- Maven

## Funcionalidades Implementadas
- Login con validación de usuario, contraseña y rol
- Menú lateral con módulos: Inicio, Productos, Categorías, Clientes, Ventas, Reportes, Usuarios
- CRUD completo de productos (Guardar, Actualizar, Eliminar)
- Tabla de productos con datos precargados
- Búsqueda de productos en tiempo real
- Badges de estado (Activo / Inactivo)
- Botón Cerrar sesión que regresa al Login

## Estructura de Carpetas
src/
└── main/
├── java/
│   └── com/example/interfaz_con_crud_javafx/mitienda/
│       ├── MainApp.java
│       ├── controller/
│       │   ├── LoginController.java
│       │   └── DashboardController.java
│       ├── model/
│       │   └── Producto.java
│       └── view/
│           ├── LoginView.java
│           └── DashboardView.java
└── resources/
└── com/example/interfaz_con_crud_javafx/mitienda/
└── styles.css

## Instrucciones para Ejecutar
1. Clonar el repositorio:
   git clone https://github.com/Jordy1199/MiTienda-JavaFX.git
2. Abrir el proyecto en IntelliJ IDEA
3. Asegurarse de tener Java 17 instalado
4. Sincronizar dependencias Maven
5. Ejecutar la clase MainApp.java

## Credenciales de Acceso
| Usuario  | Contraseña       | Rol           |
|----------|------------------|---------------|
| admin    | administrador1234 | Administrador |
| vendedor | vendedor1234     | Vendedor      |
| cajero   | cajero1234       | Cajero        |

## Autor
Jordy Cajas
Escuela Politécnica Nacional - ESFOT