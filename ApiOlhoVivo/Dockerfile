FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/spring_boot

ADD target/ApiOlhoVivo-0.0.1-SNAPSHOT.jar app.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8000

CMD java ${ADDITIONAL_OPTS} -jar app.jar --spring.profiles.active=${PROFILE}
