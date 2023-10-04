#Imagem do ambiente para a build do jar
FROM openjdk:17-alpine as builder

# Instalar o Maven Wrapper
COPY .mvn/wrapper /app/.mvn/wrapper

COPY . /app

WORKDIR /app

# # Conceder permissões de execução ao Maven Wrapper
# RUN chmod +x .mvn/wrapper/mvnw

# # Executar o Maven Wrapper para construir o projeto
# RUN ./.mvn/wrapper/mvnw clean package -DskipTests

# Definir a imagem de tempo de execução
FROM openjdk:17-alpine as runtime

#Definir o volume temporário
VOLUME /tmp

# Copiar o JAR gerado da etapa de construção para o diretório raiz do contêiner
COPY --from=builder /app/target/crud-aplication.jar crud-aplication.jar

ENTRYPOINT ["java","-jar","crud-aplication.jar"]