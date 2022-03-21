# numarics

Backend assignment for Numarics.

- As described in the given text, this small project represents communication between two spring boot microservice with separated in memory DBs.

- Architecture is mono repo, root project is numarics and all others are child (modules) of the parent.
The Project is built within the maven build tool and it is required just to run mvn clean build command from the root of the project.

- Communication between services is done with a spring rest template. (At first I started with spring cloud feign client but it introduced complexity).

- Tests(bean, endpoint, services and unit) are written just for the player service because they can be applied in the same manner to the game service.

- Search endpoint for game service are done as a happy part :) it can be optimized or better implemented.
