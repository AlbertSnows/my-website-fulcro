FROM openjdk:8-alpine
COPY target/uberjar/my-website-fulcro.jar /my-website-fulcro/app.jar
EXPOSE 3000
CMD ["java", "-jar", "/my-website-fulcro/app.jar"]