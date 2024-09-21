## Code Coverage using SonarQube and Jacoco

### Code Coverage
- It is a process of measuring how many lines of code belonging to various classes of different packages is executing during the unit testing mock test process
- The industry accepted percentage is >=80%
- We can generate Code Coverage report either using SonarLint plugin of SonarQube in eclipse IDE or using jacoco plugin of maven/gradle tool
- By seeing code coverage report u can improve our test case scenarions

### Working with SonarLint(Eclipse Plugin)
- step 1) install SonarLint in eclipse
    - help menu --> eclipse market place  --> search for sonarLint -->go--> install --> accept term and conditions --> restart the ide
- step 2) Start the following projects/services as Java Apps
  - a) Eureka Server
  - b) Admin Server
  - c) Config Server
- step 3) Run the DataCollectionService in code coverage junit Mode
    - go to "com.example" package of "src/test/java" folder --> right click --> code coverage as Junit Test

- step 4) See the report

### Working with maven jacoco plgin for code coverage
- for this we need to jacoco plgins entries in the pom.xml
- In pom.xml where jacoco plgins is configured we can add <exclude> tag to execlude ceratin package/classes partificating in the unit testing based code coverage
- Step 1) Add jacoco maven plgin link with "test" phase
- Step 2) Keeping the following server in running mode
  - a) Eureka Server
  - b) Admin Server
  - c) Config Server

- Step 3) perform maven clean package operation
 Right click on DataCollectionService  -> run as --> maven build --> goal :: clean package

- Step 3) Analaze the jacoc report
    - go to site/target/jacoco/index.html and open using browser s/w







