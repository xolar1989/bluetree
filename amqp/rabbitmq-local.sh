#!/bin/bash

docker run -d -p 5672:5672 -p 15672:15672 --name rabbit-local rabbitmq:3.9.11-management-alpine

#default credentials

#login = guest
#password = guest