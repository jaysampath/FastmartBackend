#!/bin/bash

mvn clean package

# Build Docker image
docker build -t fastmart-backend .

# Run Docker container
docker run -d -p 8080:8080 --name fastmart-backend fastmart-backend
