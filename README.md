# envisalink-dsc-rest-api
This project is a rest API to interact with the Envisalink DSC using IT100
It uses https://github.com/kmbulebu/dsc-it100-java API to establish connection and talk to the unit. I am merely exposing the API through REST.
This is work in progress for now. I will update the documentation once the direction is clearer.

This uses a subscriber pattern which means for instance you'll be able to subscribe to events emitted by your DCS alarm system. You can for an example subscribe to zone open/close, alarm off/on etc.

Written in Java and it needs to be deployed in a Java container e.g. wildfly, jboss, glassfish etc. after which it can be accessed like:

localhost:8080/envisalink/rest/module/connect/192.168.1.50

192.168.1.50 being the ip address of the envisalink or you can use the DNS name "envisalink"
