# Offer technical test AF - Alexis Malattia

## Installation
- Install and extract the archive in a folder. Make sure that maven is installed on your computer and properly configured if you have a proxy.
- If you are using an IDE, import the project as a maven project and navigate into src/main/java/com.example.offertechnicaltestaf and run the **OfferTechnicalTestAfApplication**
- If you are using a terminal, navigate into the folder where you extracted the archive and run the following command: `mvn spring-boot:run`
- The application is now running on port 8080. You can access the H2 database console on http://localhost:8080/h2-console. The JDBC URL is `jdbc:h2:file:/data/demo` and the username is `admin` with no password.

## Usage
You can use the following endpoints to interact with the application:
- GET http://localhost:8080/user/{id} to get a user by its id
- POST http://localhost:8080/user/create to create a new user

The body of the POST request on Postman should be a JSON object with the following structure:
```
{
    "name": "Alexis",
    "birthDate": "2001-04-22", 
    "countryOfResidence": "France"
}
```
*Reminder : the user must be at least 18 and also must be from France*
A user can also have two optional fields : `gender` and `phoneNumber`.

```
{
    "name": "Alexis",
    "birthDate": "2001-04-22", 
    "countryOfResidence": "France",
    "phoneNumber": "0601020304" ,
    "gender": "Male"
}
```

## Testing
You can run the tests by running the following command in the terminal: `mvn test`

