**Eligiblity Determination Module(ED Module)**
- Once the Data collection is Completed using DC Module,we need to procee for ED module to determine weather citizen eiglible for applied plans or not
- Eligiblity for plans should be decided based on plan eigibility condition and give data of the citizen
- Once the ED module completes the eligiblity determination. the data should be inserted to JR701_ED_ELGIBILITY_DETAILS db table and should be inserted to JR701_CO_TRIGGERS db table

**Eligiblity Determination Rest API**

- input==> caseNo
- output==>eligible citizen and plan info(caseNo,holderName,holderSSN,planName,planStatus,startDate,end Date,benefit amount,deniel reason)

**Plan Condition**
- SANP plan condition : if citizen employment income <=300$
- CCAP plan condition : citizen employement income <=300$ and kinds count>0 + each kids age<=16
- Medaid Plan Condition :: citizen employement income <=300$ + property income is zero
- MedCare Plan Condition :: citizen age >=65
- CAJW plan condition : citizen is job less + citizen is graduated
- QHP plan condition : Can be applied be any citizen 


**DB Table**

**JR701_ELIGIBLITY_DETERMINATION**

- ED_TRACE_ID (PK)
-  CASE_NUMBER
-  HOLDER_NAME
-  HOLDER_SSN
-  PLAN_NAME
-  PLAN_STATUS
-  PLAN_START_DATE
-  PLAN_END _DATE
-  BENIFIT_AMT
-  DENIAL-REASON


**JR701_CO_TRIGGERS**

- CO_TRIGGER_ID(PK)
- CASE_NUMBER
- CO_NOTICE_PDF(BLOB DEFAULT NULL)
- TRIGGER_STATUS(DEFAULT : PENDING)

