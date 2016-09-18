docker-build:
	docker build -t athena-schemae .

docker-push:
	docker tag athena-schemae:latest 096202052535.dkr.ecr.us-east-1.amazonaws.com/athena-schemae:latest
	docker push 096202052535.dkr.ecr.us-east-1.amazonaws.com/athena-schemae:latest
