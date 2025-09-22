# Spring Boot JPA — Fitness App 

Backend para gestión de **usuarios, rutinas y eventos fitness**.  
Implementado en **Spring Boot + JPA**, con empaquetado como **WAR** para Tomcat o como **JAR** embebido para desarrollo.

---

## Requisitos

- Java 17+
- Maven 3.9+
- Tomcat 10.1+ (WAR deployment)
- PostgreSQL 16 (o H2 en modo test)
- (Opcional) Docker / Docker Compose
- (Opcional) Postman / Insomnia para probar endpoints

---

## Estructura del proyecto

```
/SpringBootBackyardigans/
├── .git/                     
├── .mvn/                    
├── src/
│   ├── main/
│   │   ├── java/org/example/taller2/
│   │   │   ├── controller/  
│   │   │   ├── entity/       
│   │   │   ├── repository/   
│   │   │   └── service/      
│   │   └── resources/
│   │       ├── application.properties  
│   │       └── data.sql                
│   └── test/java/org/example/taller2/  
├── target/                  
├── .gitignore
├── mvnw / mvnw.cmd           
└── pom.xml                   
```

---

## Modelo de datos

Muchas relaciones **Muchos-a-Muchos** se implementan con tablas intermedias usando `@EmbeddedId`.  

### Gestión de Usuarios y Permisos
- `User` ↔ `Role` (**M:N**) → entidad `UserRole`  
- `Role` ↔ `Permission` (**M:N**) → entidad `RolePermission`  
- `User` ↔ `User` (**M:N**, entrenador-estudiante) → entidad `TrainerStudent`

### Rutinas y Ejercicios
- `Routine` ↔ `Exercise` (**M:N**) → `ExerciseRoutine`  
- `User` ↔ `Routine` (**M:N**) → `UserRoutine`  
- `UserRoutine` ↔ `RoutineVisibility` (**N:1**)  
- `Exercise` ↔ `Video` (**1:1**)  
- `Exercise` ↔ `Difficulty` (**N:1**)  
- `Exercise` ↔ `ExerciseType` (**N:1**)  

### Progreso y Recomendaciones
- `Progress` ↔ `ExerciseRoutine` (**N:1**)  
- `Progress` ↔ `Effort` (**N:1**)  
- `Recommendation` (relación ternaria)  
  - Un `User` (trainer)  
  - Otro `User` (student)  
  - Un `Progress`  

### Interacción y Eventos
- `User` ↔ `Notification` (**M:N**) → `UserNotification`  
- `Notification` ↔ `NotificationType` (**N:1**)  
- `User` ↔ `Message` (**M:N**) → `UserMessage`  
- `User` ↔ `Event` (**M:N**) → `EventParticipation`  
- `Event` ↔ `EventStatus` (**N:1**)  
- `Event` ↔ `Schedule` (**1:1**)  
- `Schedule` ↔ `Week` (**N:1**)  

Este modelo permite gran flexibilidad para construir funcionalidades de la app de fitness.

---

## Configuración

Archivo `application.properties` (dev):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fitnessdb
spring.datasource.username=app
spring.datasource.password=app
spring.jpa.hibernate.ddl-auto=validate
server.servlet.context-path=/miapp
```

---

## Ejecución

### Opción 1: JAR embebido
```bash
mvn spring-boot:run
# o
mvn clean package && java -jar target/*.jar
```

Accede en: [http://localhost:8080/ttr/health/](http://localhost:8080/ttr/health/)

### Opción 2: WAR en Tomcat
```bash
mvn clean package -DskipTests
# genera target/ttr.war
```

1. Copia `ttr.war` a `TOMCAT_HOME/webapps/`  
2. Inicia Tomcat (`bin/startup.sh` o `bin/startup.bat`)  
3. Verifica logs en `logs/catalina.out`  
4. Accede en: [http://localhost:8080/ttr/health/](http://localhost:8080/ttr/health/)


---

## Pruebas y cobertura
```bash
mvn clean verify
# Reporte JaCoCo en:
target/site/jacoco/index.html
```

---

## Checklist entrega

- [x] Entidades y relaciones (User, Role, Routine, etc.)  
- [x] Tablas intermedias con `@EmbeddedId`  
- [x] Migraciones SQL o `data.sql` con datos iniciales  
- [x] Servicios con reglas de negocio  
- [x] Pruebas unitarias y cobertura JaCoCo  
- [x] Empaquetado como WAR y probado en Tomcat  
- [x] Documentación completa (este README)  

---

## Autores

- Ruben Darío Marquinez Rincon — A00401286 — @DarioM70  
- Isabella Cuervo Vargas - A00401334 - @IsabellaCV1
- Simon Reyes Riveros - A00400880 - @SIKKING4
### Curso: Computación en Internet II — Universidad Icesi
