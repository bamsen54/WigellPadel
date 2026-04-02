# Wigell Padel API

## ADMIN Endpoints

### GET /api/v1/customers
Gets all customers.

Response (200 OK):
```json
[
  {
    "id":1, 
    "username":"simon",
    "firstName":"Simon",
    "lastName":"Toivola",
    "addresses":[],
    "bookings":[]
  }
]
```

### POST /api/v1/customers
Creates new customer

Request:
1<br>
"role" : "ADMIN" or "USER"
```json
{
  "username"  : "simon",
  "password"  : "1234", 
  "role"      : "ADMIN",
  "firstName" : "Simon",
  "lastName"  : "Toivola"
}
```

Response (201 CREATED):
```json
{
  "id"        : 1,
  "username"  : "simon",
  "role"      : "ADMIN",
  "firstName" : "Simon",
  "lastName"  : "Toivola",
  "addresses" : [],
  "bookings"  : []
}
```

### DELETE /api/v1/customers/{customerId}
Deletes Customer

Response (204 NO CONTENT)

### PUT /api/v1/customers/{customerId}
Updates Customer

```json
{
  "username"  : "simon",
  "password"  : "1234", 
  "role"      : "ADMIN",
  "firstName" : "Simon",
  "lastName"  : "Toivola"
}
```

Response (200 OK):
```json
{
  "id"        : 1,
  "username"  : "simon",
  "role"      : "ADMIN",
  "firstName" : "Simon",
  "lastName"  : "Toivola",
  "addresses" : [],
  "bookings"  : []
}
```




### POST /api/v1/customers/{customerId}/addresses
Creates address for customer with id: customerId

Request:
```json
{
  "street": "Javavägen 1",
  "postalCode": "11111",
  "city": "Jakarta"
}
```

Response (200 OK):
```json
{
  "id": 1,
  "username": "bamsen54",
  "role": "USER",
  "firstName": "Simon",
  "lastName": "Toivola",
  "addresses": [
    {
      "id": 1,
      "street": "Javavägen 1",
      "postalCode": "11111",
      "city": "Jakarta"
    }
  ],
    
  "bookings": []
}
```




