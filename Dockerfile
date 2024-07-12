# Use OpenJDK 21 as the base image
FROM openjdk:21

# Set the working directory in the Docker container
WORKDIR /app

# Copy the Gradle build file and settings to the container
COPY ./build.gradle.kts ./build.gradle.kts
COPY ./settings.gradle.kts ./settings.gradle.kts

# Copy the source code of the application to the container
COPY ./src ./src

# Install Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.4-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-*.zip
ENV GRADLE_HOME=/opt/gradle/gradle-7.4
ENV PATH=${GRADLE_HOME}/bin:${PATH}

# Build the application using Gradle
RUN gradle build --no-daemon

# Unpack the built application
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

# Use OpenJDK 21 slim image for smaller final image
FROM openjdk:21-slim

# Set the working directory in the Docker container
WORKDIR /app

# Copy the unpacked application dependencies
COPY --from=0 /app/build/dependency/BOOT-INF/lib /app/lib
COPY --from=0 /app/build/dependency/META-INF /app/META-INF
COPY --from=0 /app/build/dependency/BOOT-INF/classes /app

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-cp","app:app/lib/*","fr.carrefour.driveprojekt.DriveprojektApplication"]