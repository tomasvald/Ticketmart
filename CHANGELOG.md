# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). 

## [0.3.0] 2021.05.17

### Added

- Use of Spring Data JPA
- Several repository and service classes for data service operations
- Test methods for the repository classes
- @NamedEntityGraph annotations on entity classes for eager fetch

### Changed

- ID data type of entity classes changed from Integer to Long
- Web controllers code to use the new service classes

### Removed

- DataService and DataServiceImpl classes
- Old DataService test class

## [0.2.1] 2021.05.15

### Changed

- From Hibernate to JPA with Hibernate
- Modified the API welcome page to reference a link to the API definition

## [0.2.0] 2021.05.09

### Added

- API definition file
- Classes for customized JSON serialization of all data entities
- More database records of venues and participants
- 404 HTTP status responses when resources are not found
- Test methods for testing correct JSON serialization

### Changed

- Database table definitions to offer a more complete information of events, venues and participants
- Ticket request parameters. Changed the request body for usage of query parameters instead to follow good API development practices
- Hibernate queries from class entities to the data service class
- API website landing page with an external link to the API definition

### Removed

- JSON views due to project requeriments
- Useless log messages of data layer operations

## [0.1.0] - 2021.04.29

### Added

- JSON responses with Jackson
- JSON views
- Web controllers for ticket, event, participant and venue operations
- Test methods for testing data layer

### Changed

- Change from JAX-RS to Spring MVC
- Use of an H2 embedded database for development phase

## [0.0.1] - 2021.04.22

### Added

- API development with JAX-RS/Jersey