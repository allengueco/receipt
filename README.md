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

For your convenience, I've exposed a Swagger UI in this path: `http://localhost:8080/swagger-ui/index.html`


## Architecture

### Presentation layer
As the presentation layer is already defined provided `api.yml`, I won't go over them.

### Application layer
This layer is essentially implemented using a pipeline. Each rule is implemented using an [AbstractProcessor](./src/main/java/com/allengueco/receipt/processor/AbstractProcessor.java), which rewards points to the receipt based on its criteria.
The main handler (or the starting point of the pipeline) is the [ReceiptProcessor](./src/main/java/com/allengueco/receipt/processor/ReceiptProcessor.java), which just sums up all the points from each `AbstractProcessor`.
If we wanted to implement a new rule, we would just implement an `AbstractProcessor`.

When a `Receipt` is received from the presentation layer, a [ReceiptService](./src/main/java/com/allengueco/receipt/service/ReceiptService.java) assigns a `UUID` to the receipt as its primary key, which is then persisted by a [ReceiptRepository](./src/main/java/com/allengueco/receipt/repository/ReceiptRepository.java).
The rewards calculation is not computed until the `/receipts/{id}/points` endpoint is called.

### Persistence layer
I use Hibernate as my ORM, and Spring JPA to interact with the database layer.
There are two main entities: [Receipt](./src/main/java/com/allengueco/receipt/model/Receipt.java) and [Item](./src/main/java/com/allengueco/receipt/model/Item.java).
There is a `1-N` mapping of `Receipt -> Item`

Here are some assumptions that I've had to make when mapping the entities:
- A valid `Receipt` means it will be persisted. That means that two `Receipt`s with the same exact payload will be persisted separately.
- Each `Item` will have a unique `shortDescription`. In this context, `Item a` with `shortDescription = "  ABC"` will be different from `Item b` with `shortDescription = "ABC"`
### Database layer
I've configured an in-memory H2 Database. This makes it easier in the future to swap this out for an actual SQL database.
There is an H2 console available at `http://localhost:8080/h2-console` upon startup. Currently it is configured without any password.

## Limitations
