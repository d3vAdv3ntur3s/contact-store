# Contact Store (WIP)

This is a sample project using Spring Boot 2 with API-First design principles using Open API spec 3, demonstrates a small set of REST APIs for demo purpose.

This will be a reference point for upcoming Youtube series ^_^

...Very much work in progress in the coming weeks!

## Features

- Spring Boot 2.4.2
- API-First Design and automatic (model/api) interface generation with Open Api 3 specification
- Gradle 6.8.2 using best practice
- Prometheus Actuator + Custom info Actuator endpoint + Kubernetes Probes
- Docker-Compose for local running and configuration
- [Webflux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux) (Project Reactor)
- HTTP2 with Netty configured with self-signed TLS
- R2DBC Reactive Postgres
- DB Schema Management via Liquibase
- Reactive Global Error Handling
- Cloud Native BuildPacks
- MapStruct for mapping Domains <=> DTOs
- DX: Spring Boot Dev Tools (Code Reload/Auto Restart, Spring Boot Configuration Processor (map config to Java beans), Lombok (code generation)


### Coming soon
- Generate RESTful client stub code automatically
- Demonstration of Backpressure
- Compose integration with Prometheus, Grafana Dashboard, Grafana Loki and Tempo for logging, monitoring, and distributed tracing external aspects
- GraalVM native image compilation
- Spring Security with Oauth2 via Keycloak + In-depth security considerations
- SemVer
- Feature Flags also leveraging Spring Boots Refresh Scope
- Skaffold Kubernetes integration
- Quality control for CI
- Testing: Load Testing, Vulnerability Checks (OWASP), TestContainers
- Package via Github
- Creating a developer platform
- API Gateway + Service Mesh

## Prerequisites

- Java 11
> Note: Gradle will attempt to auto download and install the latest Java 11 Zulu build if it doesn't find an appropiate Java 11 version

- Setup local self-signed certificate, see doc: [SSL-Geneation.md](docs/SSL-Geneation.md)

## Run via Docker
Leveraging cloud native buildpacks, so there is no need for a Docker file. Configuration can be applied via Environment Variables, see `docker-compose.yml` for specifics.

### Build and Start
```shell
./gradle clean build bootBuildImage #clean build resources, build Java project and tests, and crete OCI docker image via buildpack
docker-compose up
```

### Remove Resources
```shell
docker-compose down -v #shutdown resources from compose including volume data
docker rmi contact-store:0.0.1 #remove image
```



## Run via Spring boot Jar
## IDE
To run springboot app in the IDE,
- Add `-Dspring.profiles.active=local` in VM options or environment variable `SPRING_PROFILES_ACTIVE=local`

## Command Line
To run springboot app from the commandline

> `SPRING_PROFILES_ACTIVE=local ./gradlew bootRun`

## Tests
> `./gradlew test`


## Notes

###API-First Development
Checkout Open API Spec 3 [doc](oas/openapi-1.0.0.yaml)

Project auto-generates stub interfaces for Resources and API paths which are used and implemented in the project.

The spec can be easily imported into a testing tool of choice e.g. Postman
