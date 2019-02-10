FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD /target/peixe-urbano-challenge-0.1.0.jar peixe-urbano-challenge-0.1.0.jar
ENTRYPOINT ["java","-jar","peixe-urbano-challenge-0.1.0.jar"]