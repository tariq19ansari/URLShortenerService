# URLShortenerService
URL Shortener Service
This is a simple URL shortening service implemented using Java, HTML, Spring Boot, and an H2 database.
Table of Contents
* Overview
* Technologies Used
* Setup
* Usage
* Running Locally
* Database Access
**Overview**
This project provides a URL shortening service that allows users to generate shortened URLs from long URLs and access them via a web interface.
**Technologies Used**
* Java
* Spring Boot
* HTML
* H2 Database
**Setup**
To set up the project locally, follow these steps:
* Clone the repository.
* Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
* Install the necessary dependencies using Maven or Gradle.
* Run the project locally (see Running Locally).
**Usage**
This service offers a simple web interface where users can input long URLs to generate shortened versions. The shortened URLs can then be used to redirect to the original long URLs.
Running Locally
**To run the project locally:**
* Ensure you have Java and Maven installed.
* Navigate to the project directory.
* Execute the following command:  **mvn spring-boot:run  **__  
* Open your web browser and access the service at http://localhost:8080.
**Database Access**
The H2 database console can be accessed at http://localhost:8080/h2-console while the application is running. Use the configured credentials to log in.
