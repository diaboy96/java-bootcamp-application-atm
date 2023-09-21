# java-bootcamp-application-atm
An application simulating the functionality of an ATM

# Build the application
You can build the entire application simple by running docker compose.
```shell
docker-compose build
```

# Run the application
You can run the entire application simple by running docker compose.
```shell
docker-compose up -d
```

## Application environment ##
- SwaggerUI is running at: http://localhost:8081/swagger-ui/index.html
- OpenAPI is running at: http://localhost:8081/v3/api-docs
- Adminer is running at: http://localhost:8086

### Login credentials (to test database)
| ***Field name*** | ***Value*** |
| --- | --- |
| System | PostgreSQL |
| Server | data-service-postgres |
| User | postgres |
| Password | password |
| Database | postgres |

Testing data is bootstrapped by `src/main/java/com/martindavidik/dataservice/bootstrap/BootstrapData.java`
