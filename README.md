# ideas-ingeneering
How to test:

application can be tested through postman most efficiently.

REST-Methods and End-Points:

-GET: http://localhost:8080/api/v1/news/articles        -> gives all articles
-GET: http://localhost:8080/api/v1/article/{id}         -> id is Long value, gives one article
-POST:http://localhost:8080/api/v1/article/save         -> saves article, requestbody should be json
-PUT: http://localhost:8080/api/v1/article/{id}/update  -> id is Long value, requestbody should be json
-DELETE: http://localhost:8080/api/v1/article/delete    -> requestbody should be json

DOCKER:
In order to start docker container of application from terminal:

mvn clean packege docker:build docker:run
