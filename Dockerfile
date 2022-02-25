FROM openjdk:17-alpine as builder

WORKDIR /app

COPY . .

RUN gradle bootJar

FROM openjdk:17

WORKDIR /app

EXPOSE 8080

COPY --from=builder /app/build/libs/*.jar app.jar

CMD ["java", "-jar", "/app.jar"]