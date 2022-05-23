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

Response


```
{
	{
	"welcomeMessage": "Please provide the serial number in all future requests",
	"availableRoutes": {
		"/selection&row={B}&col={2}&machine_id={uuid}": "Returns item info",
		"/getStock?machine_id={uuid}": "Return all item info",
		"/deposit&amount={225}&machine_id={uuid}": "Deposit coins into machine",
		"/loadStock": "Load and parse JSON containing new products",
		"/purchase&row={B}&col={2}&machine_id={uuid}": "Make a purchase with deposited money"
	},
	"serialNumber": "ba87ccf9-7f38-4b5f-9efc-e1a34de7e68c",
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
	]
}
```


#### REQUIRED STEPS TO DEPLOY LOCALLY:

Spring Boot:

```

git clone

cd ./vending-machine/docker

cp docker-compose.override-sample.yml docker-compose.override.yml

docker-compose up

```

Angular:

```

cd ./vending-machine/src/main/frontend

npm install

ng s

```


