### Converting Rest Service to Microservices
- To make RestService and Microservice(MS) it should be registred with Eureka server
- Eureka Server is No Doc Server,it just maintains info about registred microservice and can be used for the MS communication
- Each MS gets info about other MS for MS eureka Server in order to start the communication using client comp like DiscoveryClient, Load Balancer client or Fegin Client(Best)

### 1. develop the Eureka Server we need to place @EnableEurekaServer on top of main class(Eureka Server is Separate spring boot project)
### 2. To develop to MS we need to place @EnableDiscoveryClient on top of main class

#### step 1) Develop the Spring Boot Project as the Eureka server Project
-   Eureka Server Dependency
-   place @EnableEurekaServer on Top of main class
-   recommanded port number 8671
-   override defeault value inherited from the parent project
```
spring.application.name=EurekaServer

server.port=8761

# make this projectas eureka server by disabling parent Project
#inherited properties values to false
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

### How to register Microservices with eureka server
- In every MS Project Perform the following operation
    - place Eureka client dependecy
    - place @EnableDiscoveryClient on top of main class
    - place eureka server url in application.properties file to regsiter the MS
 
```
eureka.client.service-url.default-zone=http://localhost:8761/eureka

```
- Do it for every Rest Service /API in order to make it as Microservice

#### All our microservices are interacting with same Postgresql DB, so Instead of Placing Postgresql Datasource properties and jpa properties in every project's application.yml file ,we can place them in external config file GIT account like(Gitlab,Github)only for 1 time and we can use them in mutiple MS Projects through ConfigServer Project (spring boot project)
- for this we need to create separte Configserver Project pointing to git account having config server dependencies
- we need to add seperate ConfigClient dependecies in evenry MS Project
- Connecting ConfigServer with Git Lab Account
    - step 1) create application.properties/yml file having Postgresql datasource and jpa properties 
    - step 2) create ConfigServer Project having the following dependecies
      - 1 Config Server 
        
        - 1. place @EnableConfigServer on top of main class
        - 2. place following entires in application.yml
    ```
  server:
   port: 8888
     cloud:
       config:
        server:
          bootstrap: false
            git:
             clone-on-start: true
              skip-ssl-validation: true
               uri:https://github.com/anilghuge/javarealtimeproject.git
        
    ```
- step 3) make all the RestServices as the ConfigClients by Performing the following operations(Do it in every Rest service of the project)
    - 1) add config client starter
    ```
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
    ```
    - 2) link Rest/MS project with Config Server in application.yml file
        ```
        spring:
          config:
             import: optional:configserver:http://localhost:8888
        ```
    - 3) Remove datasource,hibernate jpa properties from application.yml file
    


### Adding Admin Server,Admin Client Feature to the Rest Projects
- step 1) Create Admin Server spring boot Project
    - 1) add web,codecentric admin server starters
    - 2) place @EnableAdminServer on the top of main class
- step 2) make all out Rest/MS Projects as the admin clients
    - 1) add admin client(codecentric) starter,spring boot actuators starters
    - 2) link MS/Rest Project with Admin server
    ```
    spring:
     boot:
        admin:
        client:
            url: http://localhost:9999
    ```
    - 3) enable all the starters in rest/MS projects
    
```
management:
  endpoint:
    env:
      enabled: true
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: '*'
```

- Note : Admin server of Spring Boot provides GUI env .. to get the actuators info about the all the Rest/Micerservices









 