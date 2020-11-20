This repo demonstrades is spring boot with GraphQL (A query language for your API). GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data. GraphQL provides a complete and understandable description of the data in your API, gives clients the power to ask for exactly what they need and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools.

Pre-requisite:
1) JDK 11
2) Eclipse / IntelliJ IDE 
3) Maven (if not part of IDE already)
4) Postman (use installed app or use it from chrome extension)

Steps to Setup :
1. Clone the application
```
https://github.com/dhruvesh-patel/spring-boot-api-graphql.git
```
2. Go to src/main/resources/application.properties file and note H2 database user name & password. 
```
spring.datasource.username=XXXXX
spring.datasource.password=XXXXX
```
3. Build and run the app using IDE / maven
```
mvn clean install 
mvn spring-boot:run
```
The app will start running - check app health using http://localhost:8351/dpinc/beneficiary/health

4. For in-memory H2 database console, Use this URL - http://localhost:8351/h2-console and below values (refer above point 2 for user name / password).
```
JDBC URL - jdbc:h2:mem:testdb
User name - xxxxx
Password - xxxxx
```
You can run below query and check USERS table is created with 5 rows in it (as we have inserted data as part of service class method at app start-up). 
```
select * from BENEFICIARY;
```
5. Explore Rest APIs using Postman. This app defines following APIs (refer "*.graphql" schema under resources folder)
```
POST http://localhost:8351//dpinc/beneficiary

Sample Post Request 1 - 
{
  beneficiary (beneficiaryId: "222") {
    beneficiaryId
	beneficiaryName	
	beneficiaryType	
	nationalId
	status
   }
  
   allBeneficiary{
   	beneficiaryId
	beneficiaryName	
	beneficiaryType
   }
}

Sample Post Request 2 - Please note, here client can pick and choose what field client wants - without changing any code on app. 

{
  beneficiary (beneficiaryId: "111") {
    beneficiaryId
	beneficiaryName	
	status
   }
  
   allBeneficiary{
   	beneficiaryName	
	beneficiaryType
	region
	country
   }
}
 
Sample Post Request 3 - Please note, here client can pick and choose what field client wants. 
{
  beneficiary (beneficiaryId: "333") {
    beneficiaryId
	beneficiaryName	
	sicCode
	orgSize
   }
}
```

As we have seen above, The advantage with GraphQL is, client can choose what all data they need from backend and accordingly choose selective fields in request (bottom line, that field shall be part of GraphQL schema). This helps to reduce unwanted fields floating around network for different needs. Also, all this can be done on back of just one REST API written in GraphQL and no change is needed as client changes request fields. With REST API, we will have to write different services to expose different data. 

