## Jenkins
- The tool automates the build and deployment process for the operations team /build and deployment team 

### Activaties in build and deployment process
- 1)Take the latest source code from GIT account like GITHUB
- 2)Compile the project source code
- 3)Execute the Unit TestCases
- 4)Perform The code Review using SonarQube
- 5)Perform the Code coverage using SonarQube or Jacoco
- 6)Package the applications
- 7)Upload the build artifact in Nexus Server(for backup)
- 8)Deploy the application in web Server(tomcat) or Application Server(GlassFish)

### Application Enviroments/Profiles
- Enviroment/Profile provides the platform that is required to executes the application/Project
  - "Dev" Env: Developer will use this "env" for code development,Integration and integration testing
  - "Test/QA" Env :Testers will use this to perform "testing" of the Project
  - "UAT" Env : User Accptence Testing(client org team will test project)
  - "Pilot" Env : it is also called as Pre-Production env ..(Generally used for Performance Testing)
  - "Prod" Env : Project goes to live i.e the endusers/employees of the client org will use this env

- Note : we generally maintain different machines for different enviroments/profiles where the same copy of the project will be build and deployed for multiple times to the machines of mutiple envs.
- Note : Instead of doing build and deployment activaties manually on mutiple env machines, it is recommaned to  automates the whole process
by taking the support of Jenkins tool

### Different Team in the Project
- Devlopment team :: Responsible for Project Development(Coding)
- Testing Team :: Responsible for Project functionality testing(Verification and Validation)
- Operations Team :: Responsible for build and deployemnt activites in different envs.

### Operations
- Build Operation : Kepping the code for execution
- Deployemnt operation :: keeping the code in the server
---------

- Dev + Ops :: devOps
Development + Operations = devlopment Operations(DevOps)

- Devops is process of collaborating development team work and Operations team work using multiple tools(DevOPs Tools)
- Using DevOps process(Tool) we can simplify the application's build and deployemnt process


## DevOps VS DevSecOps
- DevOPs/DevSecOps is not set of tools ,it is a SDLC methodology that given as extension agile methodology

## Jenkins
- it is free software that can automate various activaties related to the Project build and deployemnt process
- Jenkins is devloped in java language
- We can implement CI/CD using Jenkins 
- CI : Continous Integration
- CD :: Continous Deployemnt/Delivery

### CI/CD
- it is trending approach in SDLC
- CI/CD approach is given to simplify and automate the Project and delivery(realase) process
- CI Means -> when the code of the Project is changes must be tested immediately
- CD (Continous deployemnt) --> realease/deploy the project to Production env..
- CD (Continous Devlivery) -> Keep the Project ready in nexus kind of repository for relase purpose
- Note : For production deployemnt we need to permission from client org

### Three Project
- web Project
- Spring boot project
- Project


## Automating the build process of java Web Application Using Jenkins
- Build and Deployemnt process
  - Develop the app,test with Locak Tomcat Server -> Development
  - Commit the code to Git Repository --> Build Action
  - Download the project from GIT repository by git clone command -> Build Action
  - package the Project war file  using maven "clean package" -> deployment
  - Deploy the war file Ec2-instance Tomcat server -> deployment
  - access the application from browser -> manul testing
  
- Note - The build deployment activaties can automated using Jenkins tool

### Infrasture setup
- a) create Linux VM as AWS Ec2 instance to install Tomcat server
- b) create Linux VM as AWS Ec2 instance to install Jenkins server
- c) Clone the Git Repository to get access to the project that we want to diplay


### Whole process
- step 1) keep the java web application ready by creating it as maven web application










