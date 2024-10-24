# Zipcode API Project

The Zipcode API project is a reactive web application built with Spring Boot that provides an efficient and user-friendly way to retrieve address information based on Brazilian ZIP codes (CEPs) by getting them from the open source project https://opencep.com/. The application leverages the power of reactive programming using Project Reactor, enabling seamless handling of asynchronous data streams.

## Features

- **Address Retrieval**: Fetches address details using a provided ZIP code, with support for error handling (e.g., CEP not found).
- **Logging**: Records each address lookup for auditing and analysis purposes.
- **Mocked External API Integration**: Utilizes WireMock to simulate external API responses for testing, ensuring the application behaves correctly under different scenarios.
- **Reactive Programming**: Implements reactive programming principles to provide non-blocking and efficient data processing.
- **Comprehensive Testing**: Includes unit and integration tests using JUnit, Mockito, and StepVerifier to ensure robust application behavior.

## Technologies Used

- **Java 17**: The primary programming language for the application.mvn
- **Spring Boot**: Framework for building the application with ease and efficiency.
- **Project Reactor**: Library for building reactive applications.
- **WireMock**: Tool for mocking HTTP APIs during testing.
- **JUnit 5**: Testing framework used for unit and integration tests.

## Getting Started

To get started with the Zipcode API project, clone the repository and follow the instructions in the installation section to set up the application locally.

## Sequence Diagram

Here's a diagram about how the flow works for getting an Address with the CEP:
![Get Address](https://www.plantuml.com/plantuml/png/RO_TJi9048NlzoacNEa55WrNn1YGOA8XW24-m3eTeCcsk-pCeiRuxixoOwpIYoRjz-qvPuSiDaggdR4eFi8BKmJBa4pG_nLMmJO2Ozz8zCuT648BZieo4lEIugv2oWAVurE__gMujZCFr8mfJDw5OcFTQF6O9O-cj69VBHCiFCjQSvRFqum2Rvr9qBq3742aRI8Mw1Jsq5Ly8eMlZi4iy03GEWSroSQNi2OvBhh_qCeBDIyDPz5hr4OayA0el0vISeM-BdRNXpnZdG0dH3sxIWvGTndGX-6skpzzq-kThWjFo_diI3NRwGgsE_hPcY-MiPBCytsEnYaW6_1MW5Ir7h1rsEkOkpJiNqtA0Fy0SapGx_LXF5b0umLMFZNbcUKdPwREGtPQPKfR7VcRr1dgAzNk6m00)