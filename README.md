# GoEuro BusRoute Challenge

Readme: https://github.com/goeuro/challenges/tree/master/bus_route_challenge

# GoEuro BusRoute Challenge (Impl)

**Notes:**
_1. There was unclear about bus rout logic, so service working with assumption that all **routes from file are Bidirectional**_
_In case if it is not: we have to change architecture for stored in memory data._

_2. There was implemented **Inverted index** data structure for storing a mapping between routes and stations._

_3. _


Package:

    $ git clone https://github.com/sealTLV/bus_route_challenge_impl.git
    $ mvn clean compile package -Dmaven.test.skip=true

Run:

    $ java -jar target/bus-route-0.0.1.jar tests/docker/example

While running there is documentation available: http://localhost:8088/swagger-ui.html

    $ java -jar target/bus-route-0.0.1.jar tests/demo/f_1000_10_100
    $ java -jar target/bus-route-0.0.1.jar tests/demo/f_1000000_38712_1000
    
