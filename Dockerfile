FROM openjdk:17
COPY ./out/production/Docker/ /app.jar

ENTRYPOINT ["java","-jar","app.jar"]