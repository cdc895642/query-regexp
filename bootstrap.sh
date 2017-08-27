#!/usr/bin/env bash

    sudo apt-get update
    sudo add-apt-repository ppa:openjdk-r/ppa -y
    sudo apt-get update
      
	echo "\n----- Installing Java 8 ------\n"
    sudo apt-get -y install openjdk-8-jdk
    sudo update-alternatives --config java
	  
	echo "\n----- Installing PostgreSQL ------\n"
	sudo add-apt-repository "deb http://apt.postgresql.org/pub/repos/apt/ trusty-pgdg main"
    wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
    sudo apt-get update
    sudo apt-get install -y postgresql-9.6

    echo "\n----- Installing Git ------\n"
    sudo apt-get install -y git
    echo "\n-----Installing Maven ------\n"
    sudo apt-get install -y maven
	
	echo "-----Config PostgreSQL ------"
	sudo su postgres -c "psql -c \"CREATE DATABASE querytest;\" "
    sudo su postgres -c "psql -c \"CREATE USER query_user WITH password 'password';\" "
    sudo su postgres -c "psql -c \"GRANT ALL ON DATABASE querytest TO query_user;\" "
    
	
	sudo git clone https://github.com/cdc895642/query-regexp.git query-regexp
	
	cd /home/vagrant/query-regexp
	sudo mvn spring-boot:run
	
	