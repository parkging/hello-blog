# Use the official OpenJDK 17 image as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /etc/blog/backend

# Copy the contents of the local build directory to the container
COPY --chmod=775 ./build/libs /etc/blog/backend/build

# Set environment variable
ENV BLOG_OP_ENV=${BLOG_OP_ENV} \
    ENCRYPTION_KEY=${ENCRYPTION_KEY}

# Expose the port on which the application will run
EXPOSE 8080

# Command to run when the container starts
CMD bash -c "cd /etc/blog/backend/build/ && java -jar -Dspring.profiles.active=${BLOG_OP_ENV} blog-backend.jar"
