FROM eclipse-temurin:21

WORKDIR /app

ADD ./target/trilight-api-*.jar /app/trilight-api.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+UseStringDeduplication"

CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/trilight-api.jar"]