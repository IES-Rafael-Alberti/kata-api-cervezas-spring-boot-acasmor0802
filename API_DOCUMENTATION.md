# API REST de Cervezas - Documentación

## Información General

**Base URL:** `http://localhost:8080`

**Content-Type:** `application/json`

---

## Endpoints Disponibles

### 🍺 BEERS (CRUD Completo)

#### 1. Obtener todas las cervezas
```http
GET /beers
```

**Ejemplo con PowerShell:**
```powershell
Invoke-WebRequest -Uri http://localhost:8080/beers -Method GET -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/beers
```

**Respuesta exitosa:** `200 OK`

---

#### 2. Obtener una cerveza por ID
```http
GET /beer/{id}
```

**Ejemplo con PowerShell:**
```powershell
Invoke-WebRequest -Uri http://localhost:8080/beer/1 -Method GET -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/beer/1
```

**Respuesta exitosa:** `200 OK`

---

#### 3. Crear una nueva cerveza
```http
POST /beer
Content-Type: application/json
```

**Body (JSON):**
```json
{
    "name": "Nueva Cerveza Test",
    "breweryId": 1,
    "categoryId": 1,
    "styleId": 1,
    "abv": 5.5,
    "ibu": 30.0,
    "srm": 12.0,
    "upc": 999999,
    "filepath": "test.jpg",
    "descript": "Cerveza de prueba",
    "addUser": 1
}
```

**Ejemplo con PowerShell:**
```powershell
$body = @{
    name = "Nueva Cerveza Test"
    breweryId = 1
    categoryId = 1
    styleId = 1
    abv = 5.5
    ibu = 30.0
    srm = 12.0
    upc = 999999
    filepath = "test.jpg"
    descript = "Cerveza de prueba"
    addUser = 1
} | ConvertTo-Json

Invoke-WebRequest -Uri http://localhost:8080/beer -Method POST -Body $body -ContentType "application/json" -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X POST http://localhost:8080/beer \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nueva Cerveza Test",
    "breweryId": 1,
    "categoryId": 1,
    "styleId": 1,
    "abv": 5.5,
    "ibu": 30.0,
    "srm": 12.0,
    "upc": 999999,
    "filepath": "test.jpg",
    "descript": "Cerveza de prueba",
    "addUser": 1
  }'
```

**Respuesta exitosa:** `201 Created`

---

#### 4. Actualizar una cerveza existente
```http
PUT /beer/{id}
Content-Type: application/json
```

**Body (JSON):**
```json
{
    "name": "Cerveza Actualizada",
    "breweryId": 1,
    "categoryId": 1,
    "styleId": 1,
    "abv": 6.0,
    "ibu": 35.0,
    "srm": 15.0,
    "upc": 999999,
    "filepath": "updated.jpg",
    "descript": "Descripción actualizada",
    "addUser": 1
}
```

**Ejemplo con PowerShell:**
```powershell
$body = @{
    name = "Cerveza Actualizada"
    breweryId = 1
    categoryId = 1
    styleId = 1
    abv = 6.0
    ibu = 35.0
    srm = 15.0
    upc = 999999
    filepath = "updated.jpg"
    descript = "Descripción actualizada"
    addUser = 1
} | ConvertTo-Json

Invoke-WebRequest -Uri http://localhost:8080/beer/5928 -Method PUT -Body $body -ContentType "application/json" -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X PUT http://localhost:8080/beer/5928 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Cerveza Actualizada",
    "breweryId": 1,
    "categoryId": 1,
    "styleId": 1,
    "abv": 6.0,
    "ibu": 35.0,
    "srm": 15.0,
    "upc": 999999,
    "filepath": "updated.jpg",
    "descript": "Descripción actualizada",
    "addUser": 1
  }'
```

**Respuesta exitosa:** `200 OK`

---

#### 5. Eliminar una cerveza
```http
DELETE /beer/{id}
```

**Ejemplo con PowerShell:**
```powershell
Invoke-WebRequest -Uri http://localhost:8080/beer/5928 -Method DELETE -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X DELETE http://localhost:8080/beer/5928
```

**Respuesta exitosa:** `204 No Content`

---

### 🏭 BREWERIES (Solo lectura)

#### Obtener todas las cervecerías
```http
GET /breweries
```

**Ejemplo con PowerShell:**
```powershell
Invoke-WebRequest -Uri http://localhost:8080/breweries -Method GET -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/breweries
```

**Respuesta exitosa:** `200 OK`

**Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "name": "(512) Brewing Company",
    "address1": "407 Radam, F200",
    "address2": "",
    "city": "Austin",
    "state": "Texas",
    "code": "78745",
    "country": "United States",
    "phone": "512.707.2337",
    "website": "http://512brewing.com/",
    "descript": "..."
  }
]
```

---

### 📂 CATEGORIES (Solo lectura)

#### Obtener todas las categorías
```http
GET /categories
```

**Ejemplo con PowerShell:**
```powershell
Invoke-WebRequest -Uri http://localhost:8080/categories -Method GET -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/categories
```

**Respuesta exitosa:** `200 OK`

**Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "catName": "British Ale",
    "lastMod": "2010-10-24T13:50:10"
  },
  {
    "id": 2,
    "catName": "Irish Ale",
    "lastMod": "2010-06-08T00:00:00"
  }
]
```

---

### 🎨 STYLES (Solo lectura)

#### Obtener todos los estilos
```http
GET /styles
```

**Ejemplo con PowerShell:**
```powershell
Invoke-WebRequest -Uri http://localhost:8080/styles -Method GET -UseBasicParsing
```

**Ejemplo con curl:**
```bash
curl -X GET http://localhost:8080/styles
```

**Respuesta exitosa:** `200 OK`

**Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "category": {
      "id": 1,
      "catName": "British Ale",
      "lastMod": "2010-10-24T13:50:10"
    },
    "styleName": "Classic English-Style Pale Ale",
    "lastMod": "2010-10-24T13:53:31"
  }
]
```

---

## Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| 200 | OK - La petición se procesó correctamente |
| 201 | Created - El recurso se creó exitosamente |
| 204 | No Content - El recurso se eliminó exitosamente |
| 400 | Bad Request - Datos de entrada inválidos |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error - Error del servidor |

---

## Cómo Ejecutar la Aplicación

### Requisitos previos:
- Java 17
- MySQL 8.0
- Maven

### Pasos:

1. **Iniciar MySQL** y ejecutar el script de inicialización:
   ```bash
   mysql -u root -p < initSQL/01-create-db.sql
   ```

2. **Configurar la conexión** en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nombre_base_datos
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   ```

3. **Compilar el proyecto:**
   ```bash
   .\mvnw.cmd clean install
   ```

4. **Ejecutar la aplicación:**
   ```bash
   .\mvnw.cmd spring-boot:run
   ```

5. La API estará disponible en: `http://localhost:8080`

---

## Ejemplos de Pruebas Completas

### Flujo completo de CRUD:

```powershell
# 1. Ver todas las cervezas
Invoke-WebRequest -Uri http://localhost:8080/beers -Method GET -UseBasicParsing

# 2. Crear una nueva cerveza
$newBeer = @{
    name = "Mi Cerveza Artesanal"
    breweryId = 1
    categoryId = 3
    styleId = 10
    abv = 7.2
    ibu = 45.0
    srm = 18.0
    upc = 111222333
    filepath = "mi-cerveza.jpg"
    descript = "Una deliciosa cerveza artesanal con sabor único"
    addUser = 1
} | ConvertTo-Json

$response = Invoke-WebRequest -Uri http://localhost:8080/beer -Method POST -Body $newBeer -ContentType "application/json" -UseBasicParsing

# 3. Obtener el ID de la cerveza creada
$createdBeer = $response.Content | ConvertFrom-Json
$beerId = $createdBeer.id

# 4. Ver la cerveza creada
Invoke-WebRequest -Uri "http://localhost:8080/beer/$beerId" -Method GET -UseBasicParsing

# 5. Actualizar la cerveza
$updateBeer = @{
    name = "Mi Cerveza Artesanal PREMIUM"
    breweryId = 1
    categoryId = 3
    styleId = 10
    abv = 8.0
    ibu = 50.0
    srm = 20.0
    upc = 111222333
    filepath = "mi-cerveza-premium.jpg"
    descript = "Versión mejorada con más alcohol"
    addUser = 1
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://localhost:8080/beer/$beerId" -Method PUT -Body $updateBeer -ContentType "application/json" -UseBasicParsing

# 6. Eliminar la cerveza
Invoke-WebRequest -Uri "http://localhost:8080/beer/$beerId" -Method DELETE -UseBasicParsing
```

---

## Notas Adicionales

- Todos los endpoints de **beers** requieren los campos obligatorios: `name`, `breweryId`, `categoryId`, `styleId`
- Los valores numéricos como `abv`, `ibu`, `srm` deben ser números decimales
- Las relaciones con `brewery`, `category` y `style` deben existir previamente en la base de datos
- Los endpoints de **breweries**, **categories** y **styles** son de solo lectura (GET)

---

## Soporte

Para reportar problemas o solicitar nuevas funcionalidades, crear un issue en el repositorio del proyecto.