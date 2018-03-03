# Currency Converter
This repository contains currency converter application.

This protected currency converter application using a public currency converter API https://openexchangerates.org/signup/free, and provide a login/registration screen and a main screen to query historical or current exchange rates.

After the successful login the application can show the last 10 queries and their results on the main screen as reminder.
Here is the list of allowed currencies: EUR, USD, GBP, NZD, AUD, JPY, HUF.

**Important**
Due to api limitations all conversions will be done through USD (for example EUR -> NZD will be EUR -> USD -> NZD).

Automated build and test run done via continuous integration server https://travis-ci.org/

Latest version of application is deployed to Heroku and available via link: https://curren.herokuapp.com/

## Current build status
[![Build Status](https://travis-ci.org/ISafronenko/CurrencyConverter.svg?branch=master)](https://travis-ci.org/ISafronenko/CurrencyConverter)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Before running currency converter on your local environment you need to have:

```
Install JDK 1.8 or later (https://java.com/en/download/)
Install Maven (https://maven.apache.org/install.html)
```

You can verify you installation of *Java*:

```
$ java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
```
and *Maven*:

```
$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```
### Configuration
***Data sources***
Application using in-memory data base H2 so no additional configurations is needed.

***Build Api key***
If you want to use external continuous integration server (i.e. https://travis-ci.org/) you need to get and specify api key in properties (please read this artile. https://docs.travis-ci.com/user/deployment/heroku/). 

For example (.travis.yml):
```
api_key:
    secure: C6KL9WLAaYnHVSbJFF0bZJEgIgJgUL6rdztgyDds8UBKdHluQy+XiIaCilMJAeZb4sH4jVrPjVjsaShCwN22tAJy8zPRXx6rpGHtjUSkEuoRQkXQZ2BwadkNPJinp4/XNvA/D8us8Mz35GV76g8S9IamCS2MuxifpvfVuCxT4soRahyLOe1Vzxnu4LIvTBGXEAYAGPkc+p+2Kbo0hx1fSShqpyYM6+t8X24P3Ue5UjW1Tu8wxkBOg26C8zLD34lmxvPMB99c3IyB5YvoT/qzSfilEA1zPR8mzWyFebV9s1+s1Hhzwkd3G2X/fWcZtGSQ6z6kZpCu/v/U1WONaamj8WNt6UIJMLcQOod6HkKHxhMjTsPQ/fw1UOXc7X6PaH80KMMUUUCsRrjUd0IWYxb2lDtBdZ257Jdr+qlbPKkjq2jdEQedDQMj0aGlY8EbEBSZXCPPR9fEVmjGuXusdRW3l9720aHqIJtlorp96ic+uyP9r5fdC4bsEjTvW5bu0zfTtSYYJnQVZPQxTSKgcVAqQNgDVTYTZz/9aO2bhamVvuQsQpsibB6DRcsfTCWmcqXRjqt1qByTvBkqOdTDkCtgA5hecub7fdCJLTZ+Nxyy5rmdEoI5W1VmTQFvCPTdEFmgbs2CFuIQMZObFjPm3YXPvSYrNTjB+ZsvaypTTqeoBAw=
```
***Open Exchange Rates Api key***

For integration with https://openexchangerates.org/ you need to provide api key.
For security reason api key shouldn't appear in public github repository.
That's why you need to create env variable openexchangerates_key and assign correct api key to it.
You can register your own account at https://openexchangerates.org/ and get api key.

For **Unix** system:
To set it for current **shell and all processes started from current shell**:
```
export openexchangerates_key="3fd31199ac5b4..."
```

For **Windows**
For windows env you can add env variable using this tutorials: https://www.java.com/en/download/help/path.xml

### Running

**Important** Before running please check your environment variables and add openexchangerates_key as was mentioned before.
To get an application running on development env:

Via Maven spring-boot plugin:

```
mvn spring-boot:run
```

Or from command line:

```
mvn package
java -jar target/currency-converter-0.0.1-SNAPSHOT.jar
```

After successfull start:
```
Started CurrencyConverterApplication in 16.209 seconds (JVM running for 17.156)
```
you can use application via url http://localhost:8080/ 

## Running the tests

Testing covered by integration tests using MockMvc and architectural structure using Archunit.
To run tests execute standart maven goal:
```
mvn clean test
```
## Deployment
This application can be deployed to different cloud providers (Heroku, Digital Ocean, Google App Engine or CloudFoundry).

## Monitoring and health checking
Here are some of the most common endpoints Spring Boot provides out of the box:

/health – Shows application health information (a simple ‘status’ when accessed over an unauthenticated connection or full message details when authenticated); it’s not sensitive by default
/info – Displays arbitrary application info; not sensitive by default

## Using Swagger

All exposed endpoints are documented via Swagger.
Please check: 
* http://localhost:8080/swagger-ui.html on local host
* https://curren.herokuapp.com/swagger-ui.html on internet

## Built With

* [Spring Boot](http://start.spring.io/) - The Spring Boot Framework (Spring MVC, Spring Security, Spring Data)
* [Maven](https://maven.apache.org/) - Dependency Management
* [Travis CI](https://travis-ci.org/) - CI/CD
* [Heroku](https://dashboard.heroku.com/apps) - Hosting
* [Git](https://git-scm.com/downloads) - Version control

## Versioning

This project is using Git for version controlling.

## Authors

* **Ievgen Safronenko** - [Linkedin](https://www.linkedin.com/in/ievgen-safronenko-0ba21144/)
