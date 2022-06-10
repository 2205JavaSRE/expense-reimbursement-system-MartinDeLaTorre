#!/bin/bash

docker build -t iamadroid/project-1 .

echo "Attempting dockerhub login"
docker login -u iamadroid

echo "pushing pushing image"
docker push iamadroid/project-1

echo "running docker-compose.yml"
docker-compose up
