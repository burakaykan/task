# Spring Boot Kafka Information Collector

## :memo: Notes

This project a simple Kafka Stream Collector developed with Spring Boot.
I've followed [Kafka Quickstart](https://kafka.apache.org/quickstart) to start Kafka, create topic and publish streams.
You can find sample data in [streamData.json](https://github.com/burakaykan/task/blob/master/streamData.json).

Application expects messages from Kafka one by one.
Application configured to start from __9000__ port.

You can list Persons with 
```bash
curl --location --request GET 'http://localhost:9000/person/'
```

You can list stats of unique emails and domains with
```bash
curl --location --request GET 'http://localhost:9000/person/stats'
```

## :computer: How to execute

Clone the [repo](https://github.com/burakaykan/task), install dependencies and run.

```bash
git clone git@github.com:burakaykan/task.git
cd task
mvn clean install
mvn spring-boot:run
```

## :pushpin: Things to improve

- I can use Redis or Hazelcast for incoming messages instead of in memory list