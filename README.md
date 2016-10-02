# Address API
Address API for Irish Post Code.

It's a rest api for consult irish post code. Here is step-by-step how to use:

Clone this repo into new project folder (e.g., `address-api`).
```bash
git clone  https://github.com/kinfall/address address-api
cd address-api
```

Run maven command for target/address.jar generate. Use -P profile parameter (e.g, `dev or prod` for generete service for testing or production deploy.

```bash
mvn clean install package -P prod
```

Build the image and give the name you wish

```bash
docker build -t microservice/address:latest .
```

After build your image, run it in a Docker cointainer

```bash
docker run -P -d --name address microservice/address -p 8080:8080
```

The Microservice is up and runnig. Access your browser and check it out.

```bash
http://[localhost]:8080/postcoder-web-api/address-lookup/eircode/[NR146PZ]
```
## Next Release 1.0.1x

Working to inprove and delivere new services:

* `parameters` - request the endpoint for custom response using parameters like, format, page or line numbers.
* `startup` - improve mecanism to bulk load last request address on application startup.
* `client-html` - html 5 client for testing that hits the built service.
* `unit-test` - improve unit test to cover all mechanism of caching.
