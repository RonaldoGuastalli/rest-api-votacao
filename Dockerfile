FROM gradle:6.2.2-jdk11 as builder

RUN mkdir /app
WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src/ ./src
COPY contract/ ./contract
COPY domain/ ./domain
COPY integration/ ./integration
COPY message/ ./message
COPY exception/ ./exception

RUN gradle clean build

# multi-stage build
FROM openjdk:jdk

COPY --from=builder /app /app
WORKDIR /app

EXPOSE 8081

ENTRYPOINT ["/app/build/install/sessao-votacao/bin/sessao-votacao"]