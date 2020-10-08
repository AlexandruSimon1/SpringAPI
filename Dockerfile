FROM openjdk:14
COPY target/*.jar  /opt/docker-spring-boot.jar
ADD wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]
