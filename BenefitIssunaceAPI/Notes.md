### BI Module(Benifit Issues module)
- This module is responsilble to send benifit amount to the citizens who got approval for plans(s).
- This benifit amount wil be sent to the citizen on monthly baisc.
- Sending benifit amount to citizen is batch process with Monthly schedule.

### BI Logic
- This module should collect approved citizen data and it should create one CSV file having that data
- eg: caseNo,citizenName,SSN,BenifitAmount,AccountNumber,BankName
   - raja,545456677,300.0$,5467890876543,AMEX
   - ravi,54678900,300.0$,5678909876577,JPMC
   - jhon,545413406,300.0$,545561661,PNC Bank

- BI Module should keep this CSV file in FTP location (Remote Location)
- The Bank application will load the CSV file from the specified FTP location to perform FTP Location to Perform Money deposit transction
- ![BI_Image]()

