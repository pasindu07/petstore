# PetStore Application

## Packaging and running the application

If you want to build an _??uber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Installing CURL and WGET

    sudo apt install curl wget
    
## GET API

To get All the pets you can simply run the command,
        
    curl -X GET http://localhost:8080/v1/petStore
    
The above command sends a GET request to the provided endpoint, which returns all of the available pets. - The HTTP method of the request is defined by X. A GET request, on the other hand, does not require it to be mentioned individually. As a result, the above line is the same as,

    curl http://localhost:8080/v1/petStore
