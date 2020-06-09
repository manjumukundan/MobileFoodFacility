# MobileFoodFacility

The Mobile Food Facility is simple an application that displays nearby Food trucks and Food carts.
The application displays minimum 5 or more nearby trucks/carts based on the latitude and longitude of the position you are in.


#### Setup

The application contains a frontend client and backend service. The frontend client displays map view backed by google maps api.
The backend service exposes an api that takes users latitude/longitude and the minimum count of trucks/carts.
The application is run on localhost.

###### Using Docker

* Install Docker in your machine and install the cli for docker.
* To build the images execute: 
    - docker-compose build
* Building images will create images app-service and app-client.
* To run the images in containers execute:

        docker-image up
* Once build and run succeeds goto http://localhost:8081/
* You should see your application loaded, clicking refresh will load new results anytime you change location.

###### Using command line

* Install maven and npm in your machine - mvn cli and npm cli.
* Download the source code.
* Goto the project repository execute following to execute backend:
        
        # build service
        mvn clean package
        
        # service is executed on localhost:8080
        java -jar $(pwd)/service/target/service-1.0.0-SNAPSHOT.jar
        
        # you can test the service by calling the api like:
        # you can test api by changing latitude and longitude values in San Francisco region.
        http://localhost:8080/findfoodtrucks?lat=37.792252&lng=-122.403793&count=10
        
* Goto the project repository execute following to execute backend:

        cd user-interface
        
        # build and download dependencies
        npm clean install
        
        # execute client
        npm serve
        
        # can load client by executing
        http://localhost:8081/
        
###### Running tests

          # build tests and run tests
          mvn clean test
        