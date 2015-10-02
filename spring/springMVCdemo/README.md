#Spring MVC

##[Spring MVC guides: serving Web content](https://spring.io/guides/gs/serving-web-content/)
####Build an executable JAR

If you are using Gradle, you can run the application using ```./gradlew bootRun```.

You can build a single executable JAR file that contains all the necessary dependencies, classes, and resources. This makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

```./gradlew build```

Then you can run the JAR file:

```java -jar build/libs/gs-serving-web-content-0.1.0.jar```

If you are using Maven, you can run the application using ```mvn spring-boot:run```. Or you can 
build the JAR file with ```mvn clean package``` and run the JAR by typing:

```java -jar target/gs-serving-web-content-0.1.0.jar```

> The procedure above will create a runnable JAR. You can also opt to build a classic WAR file 
instead.


###@RequestMapping
The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.

- @RequestMapping(method=GET) to narrow this mapping.


##Understanding WAR

WAR (Web application ARchive) files are used to distribute Java-based web applications. A WAR has the same file structure as a JAR file, which is a single compressed file that contains multiple files bundled inside it.

WAR files are used to combine JSPs, servlets, Java class files, XML files, javascript libraries, JAR libraries, static web pages, and any other resources needed to run the application.

WAR files are usually deployed in servlet containers, but can also be deployed to Java EE application servers. When a WAR file is deployed to a container, the container usually unpacks it to access the files and then launches the application. With servlet containers being the most prolific platform for Java web applications, WAR files are not only a Java spec standard, but a

WAR files cannot be edited while the application is running. Any changes require rebuilding the file.