#  Backend - Sysman Materials App

Este proyecto es el backend de una aplicación para la gestión de materiales, construido con **Spring Boot 3**, **Spring Security con JWT**, **PostgreSQL**, y documentado con **Swagger / OpenAPI**.

---

## Requisitos Previos

- Tener instalado **Docker** y **Docker Compose**
- Puerto `8080` libre en tu máquina

---

## Ejecución del Proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/afrancom2/tu-repo.git
   cd fullstack.backend
   ```

2. Levanta los servicios usando Docker:

   ```bash
   docker compose up --build
   ```

3. Una vez que el contenedor esté corriendo, accede a la documentación Swagger:

   👉 http://localhost:8080/sysman/swagger-ui.html

---

## 🔐 Autenticación JWT

Para acceder a los endpoints protegidos necesitas un **token JWT**. Primero debes autenticarte.

### ✅ Endpoint de Login

```
curl --location 'http://localhost:8080/sysman/auth/login' \
--header 'Content-Type: application/json' \
--data '{
  "documentNumber": "123456",
  "password": "admin123"
}'
```

**Body (JSON):**

```json
{
  "documentNumber": "123456",
  "password": "admin123"
}
```

**Respuesta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}
```

> Guarda ese token y úsalo en las siguientes peticiones como encabezado:

```
Authorization: Bearer <tu-token>
```

---

## 📚 Documentación Swagger

Swagger UI está disponible en:

👉 [http://localhost:8080/sysman/swagger-ui.html](http://localhost:8080/sysman/swagger-ui.html)

---

## 📘 Endpoints Disponibles

| Método | Endpoint                         | Descripción                            | Auth Requerido |
|--------|----------------------------------|----------------------------------------|----------------|
| POST   | `/auth/login`                   | Login y generación de JWT              | ❌             |
| GET    | `/material`                     | Lista de materiales                    | ✅             |
| GET    | `/material/name-type`           | Filtrado por nombre y tipo             | ✅             |
| GET    | `/material/date-sale`           | Filtrado por fecha de venta            | ✅             |
| GET    | `/city/name`                    | Lista de ciudades                      | ✅             |
| GET    | `/department/name`              | Lista de departamentos                 | ✅             |

---

## 👤 Usuario de Prueba

```bash
Número de documento: 123456
Contraseña: admin123
```

Este usuario es cargado por defecto desde el script `init.sql` al inicializar la base de datos.

---

## 🗃️ Estructura del Proyecto

```
fullstack.backend
├── db                   # Script SQL para crear tablas y datos iniciales
├── config               # Configuración de seguridad y CORS
├── controller           # Controladores REST
├── entity               # Entidades JPA (User, Material, City, Department)
├── repository           # Repositorios JPA
├── service              # Lógica de negocio y autenticación
├── resources
│   ├── application.properties
└── Dockerfile + docker-compose.yml
```

---

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- PostgreSQL
- Swagger / Springdoc OpenAPI
- Docker & Docker Compose

---

## Autor

Andrés Felipe Franco Monroy
GitHub: [https://github.com/afrancom2](https://github.com/afrancom2)  
Email: afrancom2@gmail.com

---

## Notas

- Si necesitas reinicializar la base de datos, puedes bajar los contenedores y volver a correr `docker compose up --build`.
- Revisa que el archivo `init.sql` tenga la estructura correcta con los datos requeridos.