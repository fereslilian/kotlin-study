FROM gradle:6.6 as builder

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle $APP_DIR/
COPY settings.gradle $APP_DIR/
COPY gradle.properties $APP_DIR/

RUN gradle dependencies

RUN gradle build -x test --no-daemon

## -----------------------------------------------------------------------------

FROM openjdk:11-slim-buster

ENV APP_DIR /app
WORKDIR $APP_DIR

ENV JAR_NAME ktor-example*.jar

COPY --from=builder /app/build/libs/$JAR_NAME $APP_DIR

EXPOSE 8080

ENTRYPOINT java -jar $JAR_NAME
