# Online Art Store
<a name="readme-top"></a>

## Table of Contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [Installation](#installation)
* [Usage](#usage)
* [Features](#features)
* [Credits](#credits)

## Introduction
This web application lets an art supply store manage their supplies and sell to users online.
## Technologies
The application is created with:
* Java Servlet
* HTML/CSS
* MySQL
* Apache TomCat
* Netbeans 12.0

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Prerequisites
* Java SE Development Kit
* JDBC driver for MySQL v8.0.23
* KubeMQ-SDK-Java v1.0.5
* Java Protocol Buffers v3.21.12
* gRPC-Java v1.62.2

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Installation
Clone the repo:

`git clone https://github.com/icejan/Online-Art-Store.git`

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage
* Compile and run the web application on Netbeans (with TomCat)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Features

https://github.com/icejan/Online-Art-Store/assets/97641242/fabe3e93-42bc-46f5-92ef-a2fb24c35c2e

* Users can login to their account by entering their username and password 
* Users can browse items and add to their cart
* System checks the stock of each item in the user's cart to ensure the user does not buy over the limited quantity
* The database persistently stores account and cart information
* Implemented a JWT (JSON Web Token) based authentication that keeps a user logged in while using services.
* Built docker containers to deploy the services on a Kubernetes cluster in the Google Cloud.
* Enabled pub/sub messaging between services through KubeMQ event store channels to synchronize databases.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Credits
* Kubernetes
* Docker
* Google Cloud Platform (GCP)
