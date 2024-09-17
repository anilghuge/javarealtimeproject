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

--------------------------------------

### Adding Spring cloud API Gateway for the Rest projects
- API Gateway acts as the entry and exit point for all the microservices
- Using API Gatway,we can access all the microservices of the project using common url

- step 1) create spring boot project as the api gateway project
-step 4) run the project in following order
    - 1 eureka server
    - 2 Admin server
    - 3 Config Server
    - 4 Cloud API gateway
    - 5 all the microservices
- step 5) Test the MS projects using Cloud API gateway port number /url
    - 1 http://localhost:7777/plan-api/all
    - 2 -- etc.


-------------------------
### Recap on Exception Handling
- we can use try,catch and finally blocks for exception handling
- finally block executes inreespective of execption that is raised in try block
- try with single or multiple catch blocks or try with finally block is allowed
```
try{
    --
    --
}catch(--){
    // to handle known exception
}catch(--){
    // to handle known exception
}catch(Exception e){
    // to handle unknown exception
}finallly{
    //Logic to close the resources(like streams)
}
```
- Note - followed try block,first we must kept knoen exception catch blocks,later we need to place unknoen exception catch block having exception or throwable class

### can we handle mutiple exception using single catch block?
- Yes,possible
```
try{
    ---
    ----
    --
}catch(NullPointerException | IllegalArugmentexception ex){
    ---
    ---
}
```

### Why should we catch and handle the exception
- To Stop the abnormal termination of the application when the exception is raised(To continue application execution flow)
- To display non-techinal guiding message to enduser instead of techical message when the exception is raised

### What is difference between checked exception and unchecked exception
- the immediate sub class of java.lang.Exception is called checked exception 
- the sub classes(direct or indirect) of java.lang.RuntimeException is called unchecked exception class
- if the java code is dealing with checked exception then java compiler will alert u to catch and handle the exception at compile time by raising the error
- if the java code is dealing with checked exception then java compiler will alert u to catch and handle the exception at compile time
- Both checked and unchecked exception will be raised at run time

- try,catch,finally  -> for catching and handling the exceptions
- throw --> ceating the exception
- throws ---> to declaring the exception to be thrown

-------
From Java 7 onwards we got trywith resource feature i.e resuource that are opned in try statement will be closed automatically at the ned of try so we need not to close them separtely by finally block
```
try(open resuorce like stream or jdbc con or ---){
    ---
    ----
    ---//use the resource
}catch(--){}
catch(--){}
catch(Exception e){}
```

----------------
### Exception handling in our spring boot rest/spring rest project can be handling in two ways
- 1 Rest Controller specific exception handling(Specific to one RestController class)(Not Recommanded)  
- 2.gloval exception handling (common for the all exception raised in the all class of project)(Best and Recommanded)

### In Real Projects, for every custom or ready exception the Project will give our one project error code,so while exception related log file,we need to write this code 

- 1 Rest Controller specific exception handling(Specific to one RestController class)(Not Recommanded)  
    - 1) for this we need to keep @ExceptionHandler(-) method inside the @RestControllerclass i.e it will response and handling only the exception raised in that @RestController class i.e exception raised on other classes will not be handled
- 2 gloval exception handling (common for the all exception raised in the all class of project)(Best and Recommanded)
    - for this take @RestControllerAdvice separtely having @ExceptionHandler(--) method 




















 