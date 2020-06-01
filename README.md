# Envisalink 4 Rest API

This project is a rest API to interact with the Envisalink DSC using IT100.

It uses https://github.com/kmbulebu/dsc-it100-java API to establish connection and talk to the unit. I am merely exposing the API through REST.

This is work in progress for now. I will update the documentation once the direction is clearer.

## Usage

To get started, you simply start the server and it listens to port 8182 (which can be changed). You then make a REST call e.g. 

```java
http://localhost:8182/v1/envisalink/subscribe
```

in the body pass the json object like this:

```json
{
  "ipAddress":"192.168.1.134",
  "callback": {
    "url":"aHR0cDovLzE5Mi4xNjguMS43OjgwODAvQXV0b21hdGEvQ29udHJvbGxlcj9uYXY9SGFuZGxlZGV2aWNldHJpZ2dlci5tb2Jp",
    "method":"POST"
  	}
 }
 ```

if the object above, ipAddress is the ip address of your Envisalink. URL is the Base64 encoded URL that will be called if the Envisalink detects any activity e.g. door opening, alarm going off etc.

In your application you can then react to all the messages you will be receiving from this app.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
