FROM openjdk:11
ADD target/projetofinal-clientes-0.0.1-SNAPSHOT.jar projetofinal-clientes-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "projetofinal-clientes-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081