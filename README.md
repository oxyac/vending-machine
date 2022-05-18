### Vending Machine Software - Optimal Solutions Hub SRL Test Project

I developed this API using Spring Framework and some of its common utilities, such as lombok and slf4j. I used Spring Data Jpa as a data layer for database interactions.


#### API DESCRIPTION

In order to take use of this vending machine, it is necessary to pass the **serialNumber** received from the /loadStock call as a parameter for further transactions. 


    
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

Example response


```
{
	"welcomeMessage": "Here is a description of all available routes",
	"availableRoutes": {
		"/selection&row={B}&col={2}": "Returns item info",
		"/purchase&row={B}&col={2}": "Make a purchase with deposited money",
		"/getStock": "Return all item info",
		"/loadStock": "Load and parse JSON containing new products",
		"/deposit&amount={225}": "Deposit coins into machine"
	},
	"stock": {
		"id": "2c67aa3b-7020-46ec-a6d3-bb1d92f59636",
		"config": {
			"id": 1,
			"rows": "4",
			"columns": 8
		},
		"items": [
			{
				"name": "Snickers",
				"amount": 10,
				"price": 135,
				"row": "A",
				"col": 1
			},
			{
				"name": "Hersheys",
				"amount": 10,
				"price": 225,
				"row": "B",
				"col": 1
			},
			{
				"name": "Hersheys Almond",
				"amount": 10,
				"price": 180,
				"row": "C",
				"col": 1
			},
			{
				"name": "Hersheys Special Dark",
				"amount": 10,
				"price": 175,
				"row": "D",
				"col": 1
			},
			{
				"name": "Reese's",
				"amount": 10,
				"price": 105,
				"row": "A",
				"col": 2
			},
			{
				"name": "Nutrageous",
				"amount": 10,
				"price": 130,
				"row": "B",
				"col": 2
			},
			{
				"name": "Baby Ruth",
				"amount": 10,
				"price": 250,
				"row": "C",
				"col": 2
			},
			{
				"name": "Milky Way",
				"amount": 10,
				"price": 100,
				"row": "D",
				"col": 2
			},
			{
				"name": "M&M",
				"amount": 10,
				"price": 125,
				"row": "A",
				"col": 3
			}
		],
		"vendingMachine": {
			"id": "b005c6db-a0db-4843-a5b7-f792db2ef14b",
			"stock": null,
			"depositedAmount": 0,
			"transactionInProgress": true
		}
	},
	"serialNumber": "b005c6db-a0db-4843-a5b7-f792db2ef14b"
}
```


#### REQUIRED STEPS TO DEPLOY LOCALLY:



#### DEPLOY DATABASE

- Fetch image

> docker container run -d --name=pg -p 5433:5432 --user $(id -u):$(id -g) -e POSTGRES_PASSWORD=password -e PGDATA=/pgdata -v /home/og/Projects/java/vending-machine/pgdata:/pgdata postgres:latest

- Access the container:

> docker exec -it pg bash

> su postgres && psql


- Clone the project locally 

> git clone https://github.com/oxyac/vending-machine.git

> cd ./vending-machine

- Generate gradle wrapper

gradle wrapper --gradle-version 6.0.1

- Build

> ./gradlew build

- Run 

> ./gradlew bootRun 



