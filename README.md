# WigellPadel - Padel Booking API

### Author: Simon Toivola 

A REST-based microservice using JSON for request/response. 
Part of a larger microservice ecosystem

## Ports

| Service                      | Port |
|------------------------------|------|
| **WigellPadel**              | `8584` |
| **GatewayApi (external access)** | `8580` |

> The API can be accessed directly via `http://localhost:8584/` or through the gateway at `http://localhost:8580/padel/`
## Tech Stack

| Technology | Version |
|------------|---------|
| Java | 24 |
| Spring Boot | 3.5.13 |
| MySQL | 8.x |
| Maven | - |

## Authentication

The API uses Basic Auth.

### Test users

| Role | Username | Password |
|------|----------|----------|
| Admin | `a` | `1234`   |
| User | `u` | `1234`   |


## Database

```properties
Database: wigell_padel_db
Username: root
Password: root