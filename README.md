# Bike Rental API

## Descripción

Bike Rental API es una aplicación desarrollada en Spring Boot para gestionar el alquiler de bicicletas.

La solución permite:

* Consultar bicicletas disponibles.
* Consultar bicicletas disponibles por tipo.
* Registrar alquileres.
* Finalizar alquileres.
* Consultar historial de alquileres por bicicleta.
* Calcular automáticamente costos y multas por retraso.

---

## Tecnologías utilizadas

Java 17, 
Spring Boot 3.5,
Spring Data JPA,
Hibernate,
MySQL,
Maven,
Lombok,
JUnit 5

---

## Arquitectura

La aplicación está organizada utilizando una arquitectura por capas:

```text
Controller
↓
Service
↓
Repository
↓
Base de Datos
```

### Capas implementadas

* Controller
* Service
* Repository
* Entity
* DTO
* Exception Handler

Esta separación permite mantener bajo acoplamiento y una adecuada distribución de responsabilidades.

---

## Reglas de negocio implementadas

### Inicio de alquiler

* La bicicleta debe existir.
* La bicicleta debe estar disponible.
* Al iniciar el alquiler la bicicleta cambia a estado `ALQUILADA`.

### Finalización de alquiler

* Se calcula el tiempo real de uso.
* Se calcula el costo base.
* Se calcula multa cuando existe retraso.
* La bicicleta vuelve a estado `DISPONIBLE`.

### Historial

* Permite consultar todos los alquileres asociados a una bicicleta.

---

## Tarifas por hora

| Tipo de bicicleta | Tarifa |
| ----------------- | ------ |
| URBANA            | 3500   |
| MONTANA           | 5000   |
| ELECTRICA         | 7500   |

---

## Política de multas

Si el tiempo utilizado supera la duración estimada:

* Se cobra una multa equivalente al 50% de la tarifa por hora.
* La multa se calcula por cada hora adicional o fracción.

---

## Base de datos

Crear la base de datos:

```sql
CREATE DATABASE bike_rental;
```

---

## Datos de prueba

```sql
INSERT INTO bicicletas (codigo, tipo, estado)
VALUES
('BIC-001', 'URBANA', 'DISPONIBLE'),
('BIC-002', 'MONTANA', 'DISPONIBLE'),
('BIC-003', 'ELECTRICA', 'DISPONIBLE');
```
### Modelo de datos

La solución utiliza dos entidades principales:

#### Bicicleta

* id
* codigo
* tipo
* estado

#### Alquiler

* id
* nombreCliente
* horaInicio
* horaFin
* duracionEstimadaHoras
* costoTotal
* tuvoMulta
* bicicleta_id

Relación:

* Una bicicleta puede tener múltiples alquileres.
* Un alquiler pertenece a una única bicicleta.


---

## Configuración

Configurar las credenciales de MySQL en:

```properties
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bike_rental
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

---

## Ejecución

### Compilar

```bash
./mvnw clean compile
```

### Ejecutar aplicación

```bash
./mvnw spring-boot:run
```

### Ejecutar pruebas

```bash
./mvnw test
```

---

## Endpoints

### Obtener bicicletas disponibles

```http
GET /bicicletas/disponibles
```

---

### Obtener bicicletas disponibles por tipo

```http
GET /bicicletas/disponibles/tipo?tipo=URBANA
```

---

### Crear alquiler

```http
POST /alquileres
```

Body:

```json
{
  "codigoBicicleta": "BIC-001",
  "nombreCliente": "Jesus",
  "duracionEstimadaHoras": 3
}
```

---

### Finalizar alquiler

```http
POST /alquileres/{id}/finalizar
```

---

### Consultar historial

```http
GET /alquileres/historial/{codigo}
```

---

## Pruebas

Se implementaron pruebas unitarias para la lógica de cálculo de costos y multas.

Resultados actuales:

* 7 pruebas ejecutadas.
* 0 errores.
* 0 fallos.

---

## Consideraciones de diseño

Se aplicaron principios de separación de responsabilidades mediante capas especializadas.

La lógica de cálculo de costos fue desacoplada en un servicio independiente (`CostoAlquilerService`) para facilitar mantenimiento, reutilización y pruebas unitarias.

---

## Seguridad

La prueba técnica no especifica requerimientos de autenticación o autorización.

Por esta razón se implementó seguridad básica mediante:

* Validación de datos de entrada.
* Manejo centralizado de excepciones.
* Restricciones de negocio en la capa de servicio.
* Encapsulamiento de acceso a datos mediante repositorios.

La incorporación de Spring Security y JWT fue considerada fuera del alcance de los requerimientos funcionales planteados.

---

## Autor

Jesús Daniel Martínez Anaya
