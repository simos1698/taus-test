## This is a test assignment for the role of junior backend developer at TAUS.

### To test the application you need to do the following:
1. run the command
> docker pull mysql:8.3.0
2. then, run the command
> docker run --name mysql -p:3306:3306  -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=test -e MYSQL_USER=taus -e MYSQL_PASSWORD=1234 -d mysql:8.3
3. if the container is succesfully running you can clone this project and run the spring app

### A few notes about the API:
- For every request except the login we need to pass a valid jwt as bearer token for authorization. The way to acquire a valid jwt is to run the login request with valid credentials (existing in the users table) inside in the request body.
- When starting the app, along with the two tables users and tasks, the user "admin" with password "admin" is created so we can login and start testing the other endpoints
- All the requests with exemplary inputs can be found in the postman collection 
- Also in the postman collection I have set up a script at the login request to try and keep the token in an enviroment variable for ease of use. Alternatively, we need to copy and paste the acquired token each time in the Token field of the Bearer Token type authorization.

For any further questions I'm always available at symeonspyroy@gmail.com :)
