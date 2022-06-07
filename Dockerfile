
#Building stage, to build the jar file
#I require a Maven image to be able to build a maven project!

FROM maven:3.8.5-openjdk-8 AS MAVEN_BUILD_STAGE

#Copy over all the files inside of our directory!
COPY ./ ./

# cleans the project and makes the shaded jar
RUN mvn clean package


#Distributable lightweight image for running the jar file
FROM openjdk:8-jre AS PROJECT_1_JAVALIN_SERVER

COPY --from=MAVEN_BUILD_STAGE /target/project-1-MartinDeLaTorre-0.0.1-SNAPSHOT-shaded.jar /ExpenseReimbursement.jar

# TODO: set up environment variables for postgres.

EXPOSE 7600

CMD ["java","-jar","/ExpenseReimbursement.jar"]

