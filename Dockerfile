FROM maven:3.6.3-jdk-8

#copying source of framework
COPY src /home/SeleniumTestFramework/src

#copying pom.xml of framework
COPY pom.xml /home/SeleniumTestFramework


#running the command
RUN mvn -f /home/SeleniumTestFramework/pom.xml clean test -DskipTests=true