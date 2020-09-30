FROM openjdk:8
# Copy jar file
COPY target/*.jar  /out/artifacts/springapi/springapi.jar
ADD wrapper.sh wrapper.sh

RUN bash -c 'chmod +x /wrapper.sh'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]
