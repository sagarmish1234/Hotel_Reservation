@echo off

docker run -d -p 8761:8761 --name eureka-server eureka-server:latest
docker run -d -p 8000:8000 --name admin-service admin-service:latest
docker run -d -p 8888:8888 --name api-gateway api-gateway:latest
docker run -d -p 8001:8001 --name auth-service auth-service:latest

echo "Containers started"





