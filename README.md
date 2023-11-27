# RabbitMQ 
Architektura mikroserwisowa </br>
Aplikacje wykorzystujace RabbitMQ
## Info:
- Java 17
- Spring boot 2.7.17
- Maven
- H2 (Local)
- MongoDb (Remote)
- RabbitMQ
### Dependencje:
- RabbitMQ
- Spring Web
- Spring Cloud
- Spring Gateway
- Lombok
- H2
- MongoDb 
- Jackson
#### Zmiany w ostatnim commicie:
- Dodatkowe metody w CourseService
- Klasa NotificationInfoDto
- Serializacja i deserializacja (Jackson)
#### Dodatkowe informacje:
- 6 Serwisow:
  - ~~Publisher (Nieaktywny)~~
  - ~~Receiver (Nieaktywny)~~
  - Eureka
  - StudentApi
  - Gateway
  - Course (Wysyla do RabbitMq)
  - ~~(Nieaktywny) Komunikacja 2 API poprzez RabbitMQ (Publisher - Receiver)~~