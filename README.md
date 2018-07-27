Heavily derived from [ryukato's](https://github.com/ryukato/es-cqrs-axon) excellent primer on Axon framework.

The differences are as bellow
* Uses gradle instead of maven as a build tool
* Updated mongodb to 3.6.6
* Updated for latest Spring-boot (2.0.3-RELEASE)
* Has slightly different aggregates
* Command side package structure is changed to be similar to other services
* Adds permissive MIT licence
 
 # Microservices with Axon
 These projects are for the demonstration of how to build CQRS, ES(event-sourcing) microservice application that being consisted of several collaborating services. 
 
 ## Services
 There are several service projects that are the followings elements to build one logical application.
 
 * External Config Service using [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/)
 * Service Discovery Service using [Spring Cloud Euerka](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#spring-cloud-eureka-server)
 * Service gateway service using [Spring Cloud Zuul](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_router_and_filter_zuul)
 * Java microservices with [Spring boot](http://projects.spring.io/spring-boot/)
 * Comand and Query Responsibility Separation with [Axon CQRS Framework](http://www.axonframework.org/)
 * Event Sourcing with [RabbitMQ](https://www.rabbitmq.com/), [MongoDB](https://docs.mongodb.com/)
 
 ## Environment for Development
 Those projects were developed with followings.

 * Java SDK 8 (1.8.0_161)
 * Docker (18.03.1-ce, build 9ee9f40)
 * Docker-Machine (0.14.0, build 89b8332)
 * Docker-compose (1.17.0, build ac53b73)
 * Spring-boot (2.0.3.RELEASE)

 ## Run the demo
 The whole application has been packaged to be run as a series of Docker containers. So you can run the demo application with docker. 
 
 ***Before running these containers, if you have Mongo and RabbitMQ running locally, then shut them down in order to avoid port clashes***
 
 ### Clone the application codes
 You need a new folder to clone the codes and you can get the codes from git repo.
 ```
 git clone https://github.com/chandrasing/kpi.git
 ```
 ### Build docker images
 Then you can build docker container images.
 
 ```
 gradle createDockerImage
 ```
 After all builds are completed, you can see docker images

 ```
$ docker images 
REPOSITORY                   TAG                 IMAGE ID            CREATED              SIZE
kpi/query-service            latest              fc367343ba0f        36 seconds ago       265MB
kpi/gateway-service          latest              9e81ce8c967a        48 seconds ago       284MB
kpi/discovery-service        latest              1cd98dffa819        About a minute ago   277MB
kpi/config-service           latest              60f2b43a4fac        About a minute ago   227MB
kpi/command-service          latest              ad66e512390a        About a minute ago   291MB
frolvlad/alpine-oraclejdk8   slim                c7fee70d6702        6 days ago           165MB
rabbitmq                     3-management        8c06649c0351        9 days ago           149MB
mongo                        3.6.6               ceee4d1620d4        9 days ago           368MB
$
```

### Run the demo micro services
There are seven docker containers in this microservice group, they are mongo, rabbitmq, config-service, discovery-service, gateway-service, command-service and query-service.
* MongoDB: for persistent event storage for command and query.
* RabbitMQ: for event streams and event distribution.
* Config service: for external config for each service.
* Discovery service: for registering services to allow components to by flexibly located, which means provide location transparency of each service instance.
* Gateway service: for routing and load balancing traffic to command and query service.
* Command service: for processing command such as "CreateAccount", "CloseAccount" and so on.
* Query service: for providing query and view of an entity.

Using docker-compose, you can simply run the demo application with following command.

```
docker-compose -f docker-compose.yml up
```
This would aggregate console output of all the running containers
