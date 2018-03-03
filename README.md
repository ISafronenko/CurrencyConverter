# Currency Converter
This repository contains currency converter application.

This protected currency converter application using a public currency converter API https://openexchangerates.org/signup/free, and provide a login/registration screen and a main screen to query historical or current exchange rates.

After the successful login the application can show the last 10 queries and their results on the main screen as reminder.
Here is the list of allowed currencies: EUR, USD, GBP, NZD, AUD, JPY, HUF.

Automated build and test run done via continuous integration server https://travis-ci.org/

Latest version of application is deployed to Heroku and available via link: https://curren.herokuapp.com/

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

### Running

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

## Running the tests

Testing covered by integration tests using MockMvc and architectural structure using Archunit.
To run tests execute standart maven goal:
```
mvn clean test
```
## Deployment
This application can be deployed to different cloud providers (Heroku, Digital Ocean, Google App Engine or CloudFoundry).

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
