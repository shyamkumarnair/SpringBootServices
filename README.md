=======================
 User Lookup Service
=======================

Description:
This is a microservice developed using Springboot to look up users details based on their city or whose current coordinates are within a particular
miles around that city.
The development was started to find the details of users living in London or whose coordinates are within 50 miles around London. This later generalized
and converted into a generic solution which can search for users details based on the city and distance parameters passed into it.

Swagger Documentation:
Once deploed, the swagger documentation would be available on based on the port and server details.
Swagger URL : http://<Server IP/DNS Name>:<port>/swagger-ui.html
Default URL : http://localhost:8070/swagger-ui.html

API Docs:
Api docs would be available on based on the port and server details.
API Url     : http://<Server IP/DNS Name>:<port>/v2/api-docs
Default URL : http://localhost:8070/v2/api-docs

Set up:
This is a maven based project and developed using Java 8 and Springboot.
After cloning it from github, you can directly import this into your eclipse or STS (Spring tool suite) as maven project.

Services:
1. Find users living in London or whose coordinates are with in 50 miles around London. This is a no parameter service.
   URL : http://localhost:8070/users/london
   Response format :
           {
              "message": "Service messages or specific error messages",
              "status": "Service status",
              "users": [
                {
                  "city": "string",
                  "email": "string",
                  "first_name": "string",
                  "id": long,
                  "ip_address": "string",
                  "last_name": "string",
                  "latitude": double,
                  "longitude": double
                }
              ]
            }

2. Find users living in any city or whose coordinates are with in a specified distance (miles) around the city provided. 

   The following are the parameters to this service
      1. City example: London
      2. Distance : This can be any valid double value (no alphabets or no special characters other than '.') example : 50, 50.00, 2343.13232
      
   URL : http://localhost:8070/users/city/{city}/distance/{distance}
   
   Response format :
           {
              "message": "Service messages or specific error messages",
              "status": "Service status",
              "users": [
                {
                  "city": "string",
                  "email": "string",
                  "first_name": "string",
                  "id": long,
                  "ip_address": "string",
                  "last_name": "string",
                  "latitude": double,
                  "longitude": double
                }
              ]
            }
            
3. Find users living in a city. The following are the parameters to this service
      1. City example: LondonThis is a no parameter service.
      
   URL : http://localhost:8070/users/city/London
   
   Response format :
           {
              "message": "Service messages or specific error messages",
              "status": "Service status",
              "users": [
                {
                  "city": "string",
                  "email": "string",
                  "first_name": "string",
                  "id": long,
                  "ip_address": "string",
                  "last_name": "string",
                  "latitude": double,
                  "longitude": double
                }
              ]
            }
   
   
Note:

1. To transform the solution to a generic one, Google's Geocoder API service has been utilised to find the coordinates (latitude and longitude)   
of a city user entered for lookup.

2. The key used for this solution was acquired for academical or test purpose, whose usage has been limited and should not be used commercially. 
The could be service outage from Google if this been used heavily or with high volume of request.

3. All configuration required for google's geocoder can be found inside the project in application.properties file


Future Enhancements:
1. Implement Hystrix circuit breaker for fault tolerance
2. Implement cache to optimize the performance
3. To replace resttemplates with webclients to achieve reactive nature and parallell processing.
4. To containerize the solution using docker and kubernetes

Thank you.
