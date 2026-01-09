# Task Management API

API REST para gestión de tareas construida con Spring Boot 4.0.1, siguiendo arquitectura de N capas.

## Arquitectura

El proyecto sigue la arquitectura de N capas:

```
┌─────────────────────────────────────┐
│    Controllers (Presentation)      │  ← Endpoints REST
├─────────────────────────────────────┤
│    DTOs (Data Transfer Objects)    │  ← Validación y transferencia
├─────────────────────────────────────┤
│    Services (Business Logic)       │  ← Lógica de negocio
├─────────────────────────────────────┤
│    Repositories (Data Access)      │  ← Acceso a datos (JPA)
├─────────────────────────────────────┤
│    Entities (Domain Model)         │  ← Modelo de dominio
└─────────────────────────────────────┘
```

## Entidades

### 1. User (Usuario)
- Preparado para autenticación futura
- Campos: id, name, email, password, active
- Relación: Un usuario tiene muchos proyectos

### 2. Project (Proyecto)
- Agrupa tareas relacionadas
- Campos: id, name, description, active, userId
- Relación: Un proyecto pertenece a un usuario y tiene muchas tareas

### 3. Task (Tarea)
- Representa una tarea individual
- Campos: id, title, description, status, priority, dueDate, projectId
- Estados: TODO, IN_PROGRESS, DONE, CANCELLED
- Prioridades: LOW, MEDIUM, HIGH, URGENT
- Relación: Una tarea pertenece a un proyecto y puede tener muchas etiquetas

### 4. Tag (Etiqueta)
- Categorización de tareas
- Campos: id, name, color
- Relación: Muchas tareas pueden tener muchas etiquetas (ManyToMany)

## Tecnologías

- Java 17
- Spring Boot 4.0.1
- Spring Data JPA
- PostgreSQL
- Lombok
- Bean Validation

## Requisitos

- JDK 17 o superior
- PostgreSQL
- Maven 3.6+

## Configuración

### 1. Crear base de datos PostgreSQL

```sql
CREATE DATABASE taskmanagement;
```

### 2. Configurar credenciales

Edita `src/main/resources/application.properties` si necesitas cambiar las credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanagement
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## Endpoints REST

### Users (`/api/users`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/users` | Listar todos los usuarios |
| GET | `/api/users/{id}` | Obtener usuario por ID |
| GET | `/api/users/email/{email}` | Obtener usuario por email |
| POST | `/api/users` | Crear nuevo usuario |
| PUT | `/api/users/{id}` | Actualizar usuario |
| DELETE | `/api/users/{id}` | Eliminar usuario |

### Projects (`/api/projects`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/projects` | Listar todos los proyectos |
| GET | `/api/projects/{id}` | Obtener proyecto por ID |
| GET | `/api/projects/user/{userId}` | Proyectos de un usuario |
| GET | `/api/projects/user/{userId}/active` | Proyectos activos de un usuario |
| POST | `/api/projects` | Crear nuevo proyecto |
| PUT | `/api/projects/{id}` | Actualizar proyecto |
| DELETE | `/api/projects/{id}` | Eliminar proyecto |

### Tasks (`/api/tasks`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tasks` | Listar todas las tareas |
| GET | `/api/tasks/{id}` | Obtener tarea por ID |
| GET | `/api/tasks/project/{projectId}` | Tareas de un proyecto |
| GET | `/api/tasks/status/{status}` | Tareas por estado |
| GET | `/api/tasks/priority/{priority}` | Tareas por prioridad |
| POST | `/api/tasks` | Crear nueva tarea |
| PUT | `/api/tasks/{id}` | Actualizar tarea |
| DELETE | `/api/tasks/{id}` | Eliminar tarea |

### Tags (`/api/tags`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tags` | Listar todas las etiquetas |
| GET | `/api/tags/{id}` | Obtener etiqueta por ID |
| GET | `/api/tags/name/{name}` | Obtener etiqueta por nombre |
| POST | `/api/tags` | Crear nueva etiqueta |
| PUT | `/api/tags/{id}` | Actualizar etiqueta |
| DELETE | `/api/tags/{id}` | Eliminar etiqueta |

## Ejemplos de uso

### Crear un usuario

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "password": "password123"
  }'
```

### Crear un proyecto

```bash
curl -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mi Proyecto",
    "description": "Descripción del proyecto",
    "userId": 1
  }'
```

### Crear una tarea

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implementar autenticación",
    "description": "Añadir JWT a la API",
    "status": "TODO",
    "priority": "HIGH",
    "projectId": 1
  }'
```

### Crear una etiqueta

```bash
curl -X POST http://localhost:8080/api/tags \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Backend",
    "color": "#3498db"
  }'
```

## Validaciones

Todos los DTOs incluyen validaciones:

- **Email**: Debe ser válido
- **Password**: Mínimo 6 caracteres
- **Nombres**: Entre 2 y 100 caracteres
- **Color de etiquetas**: Formato hexadecimal válido (#RRGGBB)

## Manejo de errores

La API maneja los siguientes tipos de errores:

- **404 Not Found**: Recurso no encontrado
- **400 Bad Request**: Validación fallida o argumentos inválidos
- **500 Internal Server Error**: Error inesperado del servidor

Ejemplo de respuesta de error:

```json
{
  "status": 404,
  "message": "User not found with id: 99",
  "timestamp": "2025-12-19T13:45:00"
}
```

## Próximos pasos

- [ ] Implementar autenticación JWT
- [ ] Agregar Spring Security
- [ ] Implementar paginación
- [ ] Agregar filtros y búsqueda
- [ ] Documentación con Swagger/OpenAPI
- [ ] Tests unitarios e integración
- [ ] Docker y Docker Compose

## Estructura del proyecto

```
src/main/java/com/example/demo/
├── controllers/          # Capa de presentación (REST)
│   ├── UserController.java
│   ├── ProjectController.java
│   ├── TaskController.java
│   └── TagController.java
├── dto/                  # Data Transfer Objects
│   ├── UserDTO.java
│   ├── ProjectDTO.java
│   ├── TaskDTO.java
│   └── TagDTO.java
├── services/             # Capa de lógica de negocio
│   ├── UserService.java
│   ├── ProjectService.java
│   ├── TaskService.java
│   └── TagService.java
├── repositories/         # Capa de acceso a datos
│   ├── UserRepository.java
│   ├── ProjectRepository.java
│   ├── TaskRepository.java
│   └── TagRepository.java
├── entities/             # Modelo de dominio
│   ├── User.java
│   ├── Project.java
│   ├── Task.java
│   └── Tag.java
├── exceptions/           # Manejo de excepciones
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
└── DemoApplication.java  # Clase principal
```
