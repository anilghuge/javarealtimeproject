### Unit Testing and Mocking
- It is process of testing individual components of the software program/project/application
- Programmer testing on his piece of code is called unit testing
- The `unit` in unit testing can be method or class or set classes developed a progarmmer as part of his task completion
- Unit Testing is useful to identify the bugs in the application/project
- Using unit testing we can provide the quality code
- Using unit testing we can provide the bug free code
- unit testing is all about writing the test cases/test plans where differenet vaieties of inputs will be given to match expected results with actual results
- To peofrom unit testung we can use Junit and mocking Tools
- note: While wriring test cases/plans for unit testing write the test cases with the varieties of inputs and not with quantity of inputs

### Junit and Mocking
- Junit is a tool/framework that can used for performing unit testing on java application by writing different varieties test cases
- To peform isolated unit testing(not involving the dependent class/objects) any comp we can go Mocking
- The Process of creating dummay /proxy/substitude object for the real object is called Mocking
- Junit is for performing unit testing,in the unit testing if we need any mock objects then use Mocking tool
- The mocking tool are Mockito,Power Mock,JMock,Easy Mock and etc..


### Scenarioes for Isolated Unit Testing using mocking tools in combination with Junit Tool
- 1) Service class using respository/DAO lass object for saving the data..but DAO class developer is not ready with code then the service class developer creates dummany/proxy DAO class object and assign to service class class object having some dummany funtionality to complete the unit testing
- 2) Service class using respository/DAO lass object for saving the data..but the devloper do want to use real DAO class object becaise it will save the test data into DB and he wants to complete unit testing on service class then the service class developer creates dummany/proxy DAO class object and assign to service class class object having some dummany funtionality to complete the unit testing
- 3) if ine project service class is depeent service (like AR Module using SSA-web services of USA Federal Govt or e-commerce app using UPI payment service or etc..)if the external service Licence is not ready at time project devlopemnt then we need to work with Mock Object of External service in order complete unit testing on our project service class

### What is code Coverage
- This process is used to check how many lines of our application code is involved during the execution of test cases codei.e code coverage checks whether unit test cases are covering whole code of our application or not.
- Industry acceptance percentage is 80%
- we can use jacoco tool or plugin in eclipse for this code coverage(java code coverage)


### Junit Programming
- In Junit Programming we generally to two classes
-   1) Target class:: The class that u want to test
-   2) Test case class/Test class :: The class in which test cases placed for completing the unit testing with different varieties of inputs there 
-   3) we use different annotations whi;e devloping the test class/test class
-       1)@Test
-       2)@SetupOnce
-       3)@TearDown
-       etc

- 4) create Maven project as 'maven-archetype-quickstart' version 1.5 for creating project then 
    - Examples

-------------------------
### Unit Testing and Mocking
- using assertThrows method we can check whether the app raised expected exception or not

### Power Mock
- Power Mock is given to mock the objects that are dependent objects for the class on which we want to perform isolated Unit Testing
- For example if the service class is using DAO class object as the dependent object but we do not test DAO class object and we want to only Service class methods then we need to use mock object created for the DAO class

### What is problem if real DAO class obj is used as part of Unit Testing?
- Let us assume DAO class is not ready and DAO interface is ready. in this situation we create one Mock object DAO having Mock functionality and we inject that object to service class to go unit testing
- Let us assume DAO interface and DAO class is avaiale with real funtionality of persistence logic but we do not want to ise it as part unit testing becoz we do not want insert "test data" to db table records
so we create Mock/Dummay DAO class object having mock functionality to assign to service class object and to perform unit testing on service class


### What is Mocking?
- The Process of creating dummy/mock/proxy object for certain real object and injecting to target class object having some functionlaity is called Mocking

### What do u mean by isolated Testing?
- The process of seperating certain class with out involving its real dependents or assigning mock dependents and performing unit testing class is called isolated testing 









