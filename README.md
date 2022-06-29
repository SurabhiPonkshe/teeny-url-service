# teeny-url
 
Pre-requisites :
 1. Java 1.8
 2. Maven
 3. Postman - optional (to test the API functionality)

To start the application from an IDE, you can run it as a Java or Spring Boot application.
To start it from the command line, navigate to the root folder of this project and execute "mvn clean install" from the command line.
This should generate a jar file in the target folder. To run this jar, execute "java -jar target/url-shortener-0.0.1-SNAPSHOT.jar".
The above steps will get the service running on port 8080.

To test the functionality, you can use postman (or whichever tool you prefer).
REST requests to test - 

1. http://localhost:8080/getShortUrl - POST request
   Parameters - originalUrl : String (in the request body)
   This request will return the short URL.
   
2. http://localhost:8080/{shortUrl} - GET request
   Parameters - none (Path variable shortURL appended after the base URL)
   This request will return the original URL if present.
