# -------- Étape 1 : Build avec Maven --------
FROM maven:3.9.6-eclipse-temurin-21 AS builder
# Définir le dossier de travail
WORKDIR /app
# Copier uniquement le fichier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Copier tout le projet et construire l'application
COPY src ./src
RUN mvn clean package -DskipTests
# -------- Étape 2 : Image exécutable --------
FROM eclipse-temurin:21-jdk
# Dossier de travail dans le conteneur
WORKDIR /app
# Copier le jar généré depuis l'étape 1
COPY --from=builder /app/target/*.jar app.jar
# Exposer le port de l'application (par défaut 8080)
EXPOSE 8080
# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]