Building Java Projects with Gradle
--
[set up the project](https://spring.io/guides/gs/gradle/#scratch)

##build.gradle
```gradle
apply plugin: 'java'
```

- Run gradle tasks , and you see new tasks added to the list, including tasks for building the project, creating JavaDoc, and running tests.

```command
gradle build
```

- compiles, tests, and assembles the code into a JAR file


