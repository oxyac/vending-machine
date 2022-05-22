#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER vending_machine WITH PASSWORD 'vending_machine';
	CREATE DATABASE vending_machine OWNER vending_machine;
EOSQL