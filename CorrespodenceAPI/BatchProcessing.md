## Batch Processing
- Performing Builk Operations Batch by batch is called Batch Processing
- Performing same operation on multiple records repeatedly is called Batch Processing/bulk operation
- Example of batch processing  
    - Sending bulk SMS regrading Festival offer/new batches info/..
    - Sending bulk Email Regarding Festival offer/new batches info/..
    - Generate and sending Account statement to customers in evenry 3 months
    - Generate and sending Credit card bill to customer every month
    -  Calculate salaries and deposting the salaries to the customer evenry month
    

- In Every Batch Processing ,we need to enable scheduling
  - Daily Schedule
  - 12 hours schedule
  - Weekly Schedule
  - Hourly Schedule
  - Monthly Schedule
  - Half Yearly Schedule
  - Yearly Schedule
  

- Our CO Module(Correspondence Module) needs batch processing to send notices as pdf docs in email messages to citizen having daily schedule

   ![Batch Processing Image]() Fig 01
   
   - Note: Citizen Visit DHS Office 9 am to 5 pm for applying to various plans,after 5 pm after 8 pm to 10 pm the CO module related Processing should take place daily

   
## Problem in Performing the batch processing through sequential execution(existing code problem)

- service class
```
 process pendingTrigers(){
     List<COTriggersEntity> pendingEntity=triggerRepo.findByTriggerStatus("Pending");
     for(COTriggerEntity trigger:pendingEntity){
         // get Citizen Eligibility Determination Date
         // get Citizen Registration date
         // generate PDF
         // store pdf in db
         // send pdf to citizen as email attachement
         //update trigger status to completed
     }
 ...// create COSummary Object
 ..
 return COSummary
 }
 ```
- Note : if the Pending Triggers are processed sequentially as shown in above batch processing it take more time to process the huge no of triggers that are coming daily.. some days the given 2 hours might not be sufficient to process the pending triggers

## Problem use-case
- Let us assume 1 pendiing trigger is taking 1 sec time to get processed
 - in 1 min -> 60 pending triggers can be processed(3600)
 - in 60 minutes(1 hour) -> 60*60 pending triggers can be processed(3600)
 - in 120 minute(2 hour) -> 2*3600 pending triggeers can be processed(7200)
 

#### If the CO API gets more than 7200 pending triggers for processing it process max of 7200 triggers and keeps other triggees in pending state

## probelm case w.r.t the project
- Day1 ----> 3000 citizen applied for the plans --> 3000 triggers are generated and processed(OK)
- Day2 ----> 4000 citizen applied for the plans --> 4000 triggers are generated and processed(OK)
- Day3 ----> 6000 citizen applied for the plans --> 6000 triggers are generated and processed(OK)
- Day4 ----> 10,000 citizen applied for the plans --> 10,000 triggers are generated but only 7200 are processed(NOT OK)
- Day5 ----> 20,000 citizen applied for the plans --> 20,000 triggers are generated but only 7200 are processed(NOT OK)

### Every day pending triggers are getting increased and sending notices to citizen is also getting delayed from out CO module due this client org(CA State) may raise serious concern towards processing the triggers and sending the notices

### To solve this problem ,we need to use Multithreading concepts in above batch processing i.e we needs to process pending triggers by generated multiple parallel threads
- Solution using Multi Thread enable Batch Processing
- Instead of executing entire logic sequentials using 1 thread, it betters to take mutiple thread like 10 threads
- 1 thread -- 1 sec  -- to process 1 pending triggeres
- 10 parellal thread --1 sec  -- to process 10 pending triggeres(Paraell processing)
- 10 parallel threads -- 1 minute -- to process 60*10(600) pending triggers
- 10 parallel threads -- 60 minute(1 hour) -- to process 60*60*10(36000) pending triggers
- 10 parallel threads -- 2 hours -- to process 2*60*60*10(72000)pending triggers
- 20 parallel threads --> 2 hours  --- to process 2*60*60*20(1,44,000)pending triggers
 --------------------------------------

# Multi Threading
------------------
- Multi threading in java is given performing parallel activities in the projects 
- For Multi threading we need to create mutiple threads and we need assign taks to those threads
- In java ,we can create in 3 ways
    - By Implementing Runnable(I)(Funtional Interface)
    - By Extending Threda(c)
    - By Implementing callable interface(Funtional Interface)(Part of executor framework)(best)
- Collection Framework is given to work with object using vaious Data structures
- Executor Framework is given to work with multiple threads in thread pool enviroment i.e if we speify pool size the threads will be created,used and reused automatically to completed to given task
- Runnable(I) is having run() method declartion

```
 public void run(){
     return tyoe is void
 }
 ```
 - Callable(I) of Executor framwework is having call method
```
public Object call(){
   
   return type is Object 
}
```

## Sample code using Runnable(I)
---------------------------

```
public class Task1 implements Runnable {

	@Override
	public void run() {
		System.out.println("Task1.run()---->hello");
	}
	
	public static void main(String[] args) {
		Task1 task1=new Task1();
		Thread t1=new Thread(task1);
		t1.start();
		System.out.println("---------------");
		
		Task1 task2=new Task1();
		Thread t2=new Thread(task2);
		t2.start();
		
		// anonymous inner class implementing Runnable(I)
		Runnable run=new Runnable() {
			@Override
			public void run() {
				System.out.println("run() method ---> hi");
			}
		};
		
		Thread t3=new Thread(run);
		t3.start();
		
		// Lamda expression based anonymous inner class implementing Runnable(I)
		Runnable run1=()->System.out.println("lambda run() method ---> hi");
		
		Thread t4=new Thread(run1);
		t4.start();
	}

}
```
- If we give this kind of code to production, then we not increase or decrease no. of threads with out touching the source code ..(This is biggest limitation of traditional).. To overcome this problem use Executor Framework


### Sample code using Executor Framework

```
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task2 {

	public static void main(String[] args) throws Exception {
		// get threads count
		// anonymous inner class for implementing Callable(I)
		Callable<Object> callable = new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				System.out.println("Thread execution"+ Thread.currentThread());
				return "Hello";
			}
		};
		ExecutorService service=Executors.newFixedThreadPool(3);// thread pool
		for (int i = 0; i < 3; i++) {
			service.submit(callable);
		}
		//shutdown the thread
		service.awaitTermination(20, TimeUnit.SECONDS);
	}
}
```
### Running the CO Module API in Single Threaded enviroment having 6000 pending triggers(Problem Understanding)
- step 1) make sure that following db table are going to have 6000 records for testing  
        a)CO_triggers b)Citizen_Application c)Eligibility_Determination
        d) DC_Cases
- Note :- it is better to devlop PL/SQL procedures to insert the bulk records
```
CREATE OR REPLACE PROCEDURE insertRowsToCotriggers()
LANGUAGE plpgsql
AS $$
DECLARE
    i INT DEFAULT 1000;
BEGIN
    WHILE i <= 6000 LOOP
        INSERT INTO "ish-db".public.jr701_co_triggers(trigger_id, case_no, trigger_status)
        VALUES (i, i, 'Pending');
        i := i + 1;
    END LOOP;
END;
$$;

To call procedure :: call insertRowsToCotriggers() 
```
```
CREATE OR REPLACE PROCEDURE insertRowsToCitizenApplication()
LANGUAGE plpgsql
AS $$
DECLARE
    i INT DEFAULT 1000;
BEGIN
    WHILE i <= 6000 LOOP
        INSERT INTO public.jr701_citizen_application(app_id, email, full_name, ssn)
        VALUES (i, 'anilghuge@gmail.com', 'anil', (i || '4')::INT8);
        i := i + 1;
    END LOOP;
END;
$$;

- call insertRowsToCitizenApplication();

```

```
CREATE OR REPLACE PROCEDURE public.insertrowstoeligibilitydetermination()
 LANGUAGE plpgsql
AS $procedure$
DECLARE
    i INT DEFAULT 1000;
BEGIN
    WHILE i <= 6000 LOOP
        INSERT INTO public.jr701_eligiblity_determination(ed_trac_id, case_no, plan_name, plan_status,denial_reason)
        VALUES (i,i,'SNAP','Denied','HighIncome');
        i := i + 1;
    END LOOP;
END;
$procedure$
;

- call insertrowstoeligibilitydetermination();
```

```
CREATE OR REPLACE PROCEDURE public.insertrowstodccases()
 LANGUAGE plpgsql
AS $procedure$
DECLARE
    i INT DEFAULT 1000;
BEGIN
    WHILE i <= 6000 LOOP
        INSERT INTO public.jr701_dc_cases(case_no, app_id, plan_id)
        VALUES (i,i,i);
        i := i + 1;
    END LOOP;
END;
$procedure$
;

- call insertrowstodccases();
```

- step 2) improve code in co Module API

















