# 

## Model
www.msaez.io/#/180518912/storming/project

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- project
- interest
- consultation
- alarm
- salesmanmatch


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- project
```
 http :8088/projects id="id" projectname="projectname" year="year" scale="scale" target="target" host="host" summary="summary" link="link" startDate="startDate" endDate="endDate" expStartDate="expStartDate" 
```
- interest
```
 http :8088/interests id="id" projectname="projectname" date="date" phone="phone" customername="customername" matchedsalesman="matchedsalesman" interest="interest" 
```
- consultation
```
 http :8088/consultations id="id" projectname="projectname" phone="phone" customername="customername" matchedsalesman="matchedsalesman" consultationdate="consultationdate" memo="memo" step="step" 
```
- alarm
```
 http :8088/alarms id="id" eventType="eventType" eventData="eventData" salesmanId="salesmanId" timestamp="timestamp" 
```
- salesmanmatch
```
 http :8088/salesmanMatches id="id" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

