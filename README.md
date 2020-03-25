Change in the properties file (application.properties) parameters for connecting to the PostgreSQL database: 

**spring.datasource.url** - URL of the database connection, 

**spring.datasource.username** - username of the database user, 

**spring.datasource.password** - password of the database user.

Run the TestrestApplication.
Open a web page http://localhost:8080/swagger-ui.html.

Click the **event-controller** tab and select a method to check:

Click the Try it out button, then enter the input data and click the Execute button. 
 
**POST /event/add**(input data in JSON format) creates a visit event for a user:
```$javascript
{
  "pageId": 1,
  "userId": 1
}
```
The response will be statistics for the day:
```$javascript
{
  "amountAllVisits": 3,
  "amountUniqueUsers": 1
}
```
**GET /event/statistics** gets a session statistics for any period:

`from` - date in the format ISO 8601 format (YYYY-MM-DD)

`to` - date in the format ISO 8601 format (YYYY-MM-DD)

Example: `http://localhost:8080/event/statistics?from=2020-03-24&to=2020-03-25` - **statistics for one day**

The response will be statistics for the day:
```$javascript
{
  "amountAllVisits": 36,
  "amountUniqueUsers": 5,
  "amountRegularUsers": 0
}
```

