# Connect Kafka using Spring in JSON format

### Reference Documentation
For further reference, please consider the following sections:

* [Gradle documentation](https://docs.gradle.org)
* [Docker Compose Overview](https://docs.docker.com/compose/overview/) 
* [Spring Boot Kafka Tutorial](https://www.tutorialspoint.com/spring_boot/spring_boot_apache_kafka.htm)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [Gradle Parent/Multi-Project Setup](https://docs.gradle.org/current/userguide/multi_project_builds.html)
* [Jacoco - Code Coverage](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
* [Novoda - Static Analysis](https://github.com/novoda/gradle-static-analysis-plugin)
* [Spotbugs - Static Analysis](https://spotbugs.github.io/)

### Build & Unit Test & Coverage & Static Analysis
```
$ docker-compose up build-penr-oz-kafka-spring
```

### Integration Test
```
$ docker-compose up itest-penr-oz-kafka-spring
```

### Run
```
$ docker-compose up penr-oz-kafka-spring
```

### Debug
[docker-compose.override.yml](https://docs.docker.com/compose/extends/)
```
version: '3'
services:
  penr-oz-kafka-spring:
    entrypoint: java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
    ports:
      - "5005:5005"
```
then
```
$ docker-compose up penr-oz-kafka-spring
```
finally use your favorite IDE to put breakpoints and connect to debugger
e.g.
[Intellij Remote Java](https://www.jetbrains.com/help/idea/run-debug-configuration-remote-debug.html)
which requires a correct local [JDK](https://jdk.java.net/13/)
installed.

### Stop
```
$ docker-compose stop
```

### Clean
```
$ docker-compose down
```

[docker-compose.override.yml](https://docs.docker.com/compose/extends/)
```
version: '3'
services:
  build-penr-oz-kafka-spring:
    command: clean
```

then
```
$ docker-compose up build-penr-oz-kafka-spring
```

### Check/Health
```
docker-compose ps
```