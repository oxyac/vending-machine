Vending Machine Software - Optimal Solutions Hub SRL Test Project

service setup as follows:

$ docker container run -d --name=pg -p 5433:5432 --user $(id -u):$(id -g) -e POSTGRES_PASSWORD=password -e PGDATA=/pgdata -v /home/og/Projects/java/vending-machine/pgdata:/pgdata postgres:latest

$ docker exec -it pg bash
$ su postgres
$ psql


psql> CREATE DATABASE md_oxyac_store;
CREATE USER og WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE md_oxyac_store TO og;