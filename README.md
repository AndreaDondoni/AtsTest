# ATMs Point Test Project 

## Description
The test project is develop using Spring (4.3.2.RELEASE) an Maven as required.

## Environment
- Windows 10
- IDE: Neon.2 Release (4.6.2)
- Server: Apache Tomcat 7.0.96
- Java: 1.8.0_121

## Solution implemented

Project is created using Spring framework and inject configuration using annotation.

In order to implement a REST service, was defined a **ServiceController** class.

This class is defined as ```@RestController``` class and expose two services:
- **/list**: an HTTP/GET service that retrieve all ATMs Points;
- **/search**: an HTTP/POST service, that perform a search into ATMs Point available. Body request contains a query text to apply.

Both services return a **BaseResponse** contains a *message* text, and a *result* with a list of ATMs Point available.

The request elaboration is delegated to **ATMService** class.
This class is defined as ```@Service``` and implement a business logic.
Into class is implemented a logic to retrieve data from external URI and to perform 
a filtering into retrieved data.

A servlet is configured with path ** /rest/* **

## JSON process
In order to process JSON file has been introduced a GSON library.
Using GSON it was possible to retrieve the data from streamReader and map result into a list of **AtmPoint** object.

Search logic was done using java 8 stream feature.

JSON object was mapping creating a DTO object **AtmPoint** and  **Address**

## Presentation Layer
Presentation layer was implemented creating a simple jsp and using *jquery* and *bootstrap* webjars.
All logic is implemented into **index.jsp** page.

The page presents on top an alert panel within which are shows information to user.
Under that there is a search bar to perform search operation and then are shows well formed JSON
response with all data available.

On page startup, using jquery is invoked, a *list* rest service that retrieves data from external service,
process it and return a response within message and result.
The message is show into jsp alert panel and the result is show in the feedback panel.

When the user perform a search operation, using jquery, is invoked a *search* POST service, sending
text to find into body param.

Page is available on url: ```http://localhost:8080/AtmProject/index.jsp```

## Security Layer
Security Layer is implemented using Spring Security Framework.

All configuration was done using annotation; about that was created following classes:
- **SecurityConfiguration**: creates a Servlet Filter known as the springSecurityFilterChain which is responsible for all the security (protecting the application URLs, validating submitted username and passwords, redirecting to the log in form, etc) within our application;
- **SecurityWebApplicationInitializer**: initializer class registers the springSecurityFilter

Into SecurityConfiguration there is a logic to add authorization access to page index.jsp only to USER role.

Access page is available logging with credential ```ats/test```


## Unit test
In order to test services was created a **MockControllerTest** class that using Mockito and performs following test:
- testListService: test that list service is available.
- testSearchService: test that search service is available and return a specific ATMs Point searched.
- testSearchService_notFound: negative test in order to verify that search service returns a specific message if no atm point was been found.

## Apache Camel routing
Not done.
