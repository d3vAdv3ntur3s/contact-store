#!/usr/bin/env bash

# Exit immediately, do not continue running commands after error
set -o errexit

##################################### NOTE ############################################
# Anything copied into the /docker-entrypoint-initdb.d will be executed automatically #
#                                                                                     #
# This script will work for dockerised or system installed Postgres                   #
#######################################################################################

# Common configuration properties
psqluser=${POSTGRES_USER:-contact_store}
psqlpasswd=${POSTGRES_PASSWORD:-contact_store}
psqldb=${POSTGRES_DB:-contact_store}
psqlschema=contact_store

## Create user
#createuser $psqluser && echo "CREATE ROLE"
psql -U $psqluser -c "SELECT 1 FROM pg_user WHERE usename = '$psqluser'" | grep -q 1 \
|| psql -U postgres -c "CREATE ROLE $psqluser LOGIN PASSWORD $psqlpasswd;"

# Create database and user as owner
psql -U $psqluser -c "SELECT 1 FROM pg_database WHERE datname = '$psqldb'" | grep -q 1 || psql -U $psqluser -c "CREATE DATABASE $psqldb"
echo "DATABASE $psqldb"

# Grant all privileges for user on the database
psql -U "$psqluser" -c "GRANT ALL PRIVILEGES ON DATABASE $psqldb TO $psqluser;"
echo "Granted all privileges on $psqldb for $psqluser"

## Create schema under the database
psql -U "$psqluser" -c "CREATE SCHEMA IF NOT EXISTS $psqlschema AUTHORIZATION $psqluser;"
echo "SCHEMA $psqlschema authorised by $psqluser"

## Create extension uuid-ossp
psql -U "$psqluser" -c "CREATE EXTENSION IF NOT EXISTS \"pgcrypto\" with SCHEMA $psqlschema;"
echo "EXTENSION created pgcrypto for SCHEMA $psqlschema"

## Alter user to add a password
#psql "$psqldb" -c "ALTER USER $psqluser WITH PASSWORD '$psqlpasswd';"
