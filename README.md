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
![Get Address](http://www.plantuml.com/plantuml/png/ZP31JW8n48RlVOe99ptWek54Z0499Xe26E83rEu0cynkIsUA6kExssvTmoOUt5lxVVt_dPcoQ2-X9gMDM0ylZ1uqGr0ktbUcSheHcDj6l2NAp40FpihI8_CM_Qaoc0J-Yo_j_aoXlhDssCpHBTu5VQDfjdbCaZMgrA9VDIDiBCi-vcoVbmavFf8ACBm3Cm6Fnu0iC2XqxYe-3RglWJ99u0aOJGGroi6Mi4Vv-QFxZrXv4SrBnKcqCUeWudXI5DPX4ycLiNLnkivvIfC01sEGUHSefZ8gY1_3xR1zVJCQTUF2qtQzKaYCC1wDORxOG6C5TZOqvVdB__JsEhgqLKnxI6ajDWbpIzjpIETiAuoD-SjpKlHOd_1tRsd9YbgMzfffDqXBuQq0gUeewzgfhgmtQjVOkvp6xb3JDm00)