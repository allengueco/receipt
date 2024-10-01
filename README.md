# Fetch Receipt Challenge Submission
---
This is a Java 21 solution using Spring Boot and Hibernate. The default port is `8080`. 
## Steps to run
1) Clone the repository.
```shell
git clone https://github.com/allengueco/receipt.git
```

2) Build the Docker Image.
```shell
docker build -t receipt .
```

3) Run the Docker Container.
```shell
docker run -p 8080:8080 receipt
```

For your convenience, I've exposed a Swagger UI in this path: `http://localhost:8080/swagger-ui/index.html`
