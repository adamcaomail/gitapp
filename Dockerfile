FROM alpine/git AS gitstage
ARG git_url
WORKDIR /gitapp
RUN git clone ${git_url}

FROM maven:3.8-openjdk-11 AS mvnstage
ARG project_name
ARG project_version
WORKDIR /mvnapp
COPY --from=gitstage /gitapp/${project_name} ./
RUN mvn package


FROM tomcat:9.0-alpine

COPY --from=mvnstage /mvnapp/${project_name}-${project_version}.war /usr/local/tomcat/webapps/
EXPOSE 8081