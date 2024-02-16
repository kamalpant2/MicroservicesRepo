1. studentinfo is the main service which has mainly four endpoints for CRUD operation and endpoint /getStudentDetail is calling companyinformation service for fetching company detail 
and embedding with student data.
2. Other microservice components are created seperatly as of now and are getting integrated with studentinfo and companyinformation step by step.
3. Unit test cases are written for studentinfo service class using Junit and Mockito
