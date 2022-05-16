### Vending Machine Software - Optimal Solutions Hub SRL Test Project

I developed this API using Spring Framework and some of its common utilities, such as lombok and slf4j.
The application is not coupled to a database, but can easily be configured using the docker image below.


#### REQUIRED STEPS TO DEPLOY LOCALLY:



- Clone the project locally 

> git clone https://github.com/oxyac/vending-machine.git

> cd ./vending-machine

- Generate gradle wrapper

gradle wrapper --gradle-version 6.0.1

- Build

> ./gradlew build

- Run 

> ./gradlew bootRun 


#### API DESCRIPTION
```
"availableRoutes": {
        "/selection&row={B}&col={2}": "Returns item info",
        "/purchase&row={B}&col={2}": "Make a purchase with deposited money",
        "/getStock": "Return all item info",
        "/loadStock": "Load and parse JSON containing new products",
        "/deposit&amount={225}": "Deposit coins into machine"
    }
```
    
#### JSON sample for /loadStock route:


```
{
	"config": {
		"rows": 4,
		"columns": "8"
	},
	"items": [{
		"name": "Snickers",
		"amount": 10,
		"price": "$1.35"
	}, {
		"name": "Hersheys",
		"amount": 10,
		"price": "$2.25"
	}, {
		"name": "Hersheys Almond",
		"amount": 10,
		"price": "$1.80"
	}, {
		"name": "Hersheys Special Dark",
		"amount": 10,
		"price": "$1.75"
	}, {
		"name": "Reese's",
		"amount": 10,
		"price": "$1.05"
	}, {
		"name": "Nutrageous",
		"amount": 10,
		"price": "$1.30"
	}, {
		"name": "Baby Ruth",
		"amount": 10,
		"price": "$2.50"
	}, {
		"name": "Milky Way",
		"amount": 10,
		"price": "$1.00"
	}, {
		"name": "M&M",
		"amount": 10,
		"price": "$1.25"
	}]
}
```

#### DEPLOY DATABASE

- Fetch image

> docker container run -d --name=pg -p 5433:5432 --user $(id -u):$(id -g) -e POSTGRES_PASSWORD=password -e PGDATA=/pgdata -v /home/og/Projects/java/vending-machine/pgdata:/pgdata postgres:latest

- Access the container:

> docker exec -it pg bash

> su postgres && psql

