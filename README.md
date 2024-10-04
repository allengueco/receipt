# Fetch Receipt Challenge Submission
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

4) Wait for the server to start. It should say something like this:
```
Started ReceiptApplication in 2.592 seconds (process running for 2.807)
```

For your convenience, I've exposed a Swagger UI in this path: `http://localhost:8080/swagger-ui/index.html`


## Architecture

### API layer
As the API layer is already provided in `api.yml`, I won't go over them.

### Application layer
This layer is essentially implemented using a pipeline. Each rule is implemented using an [AbstractProcessor](./src/main/java/com/allengueco/receipt/processor/AbstractProcessor.java), which awards points to the receipt based on its criteria.
The main handler (or the starting point of the pipeline) is the [ReceiptProcessor](./src/main/java/com/allengueco/receipt/processor/ReceiptProcessor.java), which just sums up all the points from each `AbstractProcessor`.
If we wanted to implement a new rule, we would just implement an `AbstractProcessor`. The `ReceiptProcessor` would automatically inject (autowire) the new `AbstractProcessor`.

When a `Receipt` is received from the API layer, a [ReceiptService](./src/main/java/com/allengueco/receipt/service/ReceiptService.java) assigns a `UUID` to the receipt as its primary key when it is persisted by the [ReceiptRepository](./src/main/java/com/allengueco/receipt/repository/ReceiptRepository.java).

The rewards calculation is not computed until the `/receipts/{id}/points` endpoint is called.

### Persistence layer
I use Hibernate as my ORM, and Spring JPA to interact with the database layer through the [ReceiptRepository](./src/main/java/com/allengueco/receipt/repository/ReceiptRepository.java).
There are two main entites: [Receipt](./src/main/java/com/allengueco/receipt/model/Receipt.java) and [Item](./src/main/java/com/allengueco/receipt/model/Item.java).

Aside from the defined `Regex`s defined in `api.yml`, I've also opted to put a `@PastOrPresent` validation on the `purchaseDate`.
Logically, all `Receipt`s to be processed would have already happened.

Here are some assumptions that I've had to make when mapping the entities:
- A valid `Receipt` means it will be persisted.
    - Two `Receipt`s with the same exact payload will be persisted separately.
- There is a `1-N` mapping of `Receipt -> Item`.
    - Two `Receipt`s that have the same exact `Item` (i.e. same `shortDescription` and `price`) will persist the `Item` twice with different primary ids.
    - This also means that the `Receipt` "owns" the `Item`s. If we delete a `Receipt`, we also delete all its `Item`s.

### Database layer
I've configured an in-memory H2 Database. This makes it easier in the future to swap this out for an actual SQL database.
There is an H2 console available at `http://localhost:8080/h2-console` upon startup.
Currently it is configured without any password, so pressing `Connect` will give you access to the database. (See [application.yml](./src/main/resources/application.properties) for details) 

## Tests
There are some simple integration tests [here](src/test/java/com/allengueco/receipt/ReceiptApplicationTests.java).

## Limitations / Areas for improvement

This is missing a lot of tests. Input validation is missing for the request bodies.

I also could have chosen to store the points of the `Receipt` whenever the `/receipts/{id}/points` is hit. 
My main thought process was that amends (`PATCH` requests) could be done to add or remove items. 
But realistically, if a change has to be made for a `Receipt`, we would make more sense to just generate a new one and store that.

Since the points calculation is calculated every time `/receipts/{id}/points` is hit, we can implement a caching layer to make computation lighter. 
