# Wigell Padel API

## Endpoints

### POST /api/v1/customers
Skapar en ny kund.

Request:
{
    "username":"simon",
    "password":"hejsan123", 
    "role": "ADMIN", 
    "firstName":"Simon",
    "lastName":"Wigell"
}

Response (201 Created):
{"id":1,"username":"simon","firstName":"Simon","lastName":"Wigell","addresses":[],"bookings":[]}

### GET /api/v1/customers
Hämtar alla kunder.

Response (200 OK):
[{"id":1,"username":"simon","firstName":"Simon","lastName":"Wigell","addresses":[],"bookings":[]}]

### POST /api/v1/customers/{customerId}/addresses
Lägger till adress för en kund.

Request:
{
    "street":"Storgatan 1",
    "city":"Stockholm",
    "postalCode":"111 22"
}

Response (201 Created):
{"id":1,"username":"simon","firstName":"Simon","lastName":"Wigell","addresses":[{"id":1,"street":"Storgatan 1","city":"Stockholm","postalCode":"111 22"}],"bookings":[]}