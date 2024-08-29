## Correspondence API
- Once the ED module completes the Eligibility Determination,that module inserts a record into CO_triggers db table to send notice to the citizen who applied for the Plan
 [Both citizen data  whose plan is approved and plan is denied will be inserted to CO_triggers db table]

- CO Module is responsible to send notices to the citizen  in the form email having pdf docs who applied for the  plans
- The PDF document attached to the mail should have Citizen Plan eligibility details
- CO module(Correspondance) uses CO_triggers db table to send notices to citizen as pdf docs attached to the emails
- This CO Module collects all pending details from CO_trigger db table ,prepares pdf for them,sends mails to having pdf attachement and also update status to completed

----------------------------------

## Correspondance API Development

-  1.create spring starter project having the following starters  
    web ,data jpa,open pdf,mail,swagger3,devtool,Postgres,lombok
-  2.create entity and DAO from ED module
-  3.develop the binding class that contains the corespodenace summary
-  4.add entry in application.yml file having postgres datasource         properties and mail properties
```
spring:
  application:
    name: CorrespodenceAPI
  datasource:
    driver-class-name: org.postgresql.Driver
    password: root123
    url: jdbc:postgresql://localhost:5432/ish-db
    username: postgres
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
  mail:
    host: smtp.gmail.com
    port: 587
    username: anilghuge314@gmail.com
    password: lodclajoaxevijfa
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true

```
- 5.develop the service interafce and service impl class
