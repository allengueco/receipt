package com.allengueco.receipt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.allengueco.receipt.model.ReceiptId;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ReceiptApplicationTests {

    @LocalServerPort
    int port;
    // @Autowired
    // MockMvc mvc;    

    @Test
    public void submitValidReceipt() throws Exception {
        var valid = """
                    {
                        "retailer": "Target",
                        "purchaseDate": "2022-01-02",
                        "purchaseTime": "13:13",
                        "total": "1.25",
                        "items": [
                            {"shortDescription": "Pepsi - 12-oz", "price": "1.25"}
                        ]
                    }
                """;

        ReceiptId id = RestClient.create()
            .post()
            .uri("http://localhost:{port}/receipts/process", port)
            .contentType(MediaType.APPLICATION_JSON)
            .body(valid).retrieve()
            .body(ReceiptId.class);

        assertNotNull(id);

        // mvc.perform(post("/receipts/process").content(valid).contentType(MediaType.APPLICATION_JSON))
        //         .andDo(print())
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("$.id").isString());
    }

    // @Test
    // public void calculatePoints() throws Exception {
    //     var valid = """
    //             {
    //                 "retailer": "Target",
    //                 "purchaseDate": "2022-01-01",
    //                 "purchaseTime": "13:01",
    //                 "items": [
    //                     {
    //                        "shortDescription": "Mountain Dew 12PK",
    //                        "price": "6.49"
    //                     },
    //                     {
    //                         "shortDescription": "Emils Cheese Pizza",
    //                           "price": "12.25"
    //                     },
    //                     {
    //                         "shortDescription": "Knorr Creamy Chicken",
    //                         "price": "1.26"
    //                     },
    //                     {
    //                         "shortDescription": "Doritos Nacho Cheese",
    //                         "price": "3.35"
    //                     },
    //                     {
    //                         "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
    //                         "price": "12.00"
    //                     }
    //                 ],
    //                 "total": "35.35"
    //             }
    //             """;
    //
    //     MvcResult result = mvc.perform(post("/receipts/process")
    //             .content(valid).contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.id").isString())
    //             .andReturn();
    //
    //     String body = result.getResponse().getContentAsString();
    //
    //     var mapper = new ObjectMapper();
    //     ReceiptId id = mapper.readValue(body, ReceiptId.class);
    //
    //     assertNotNull(id.id());
    //
    //     mvc.perform(get("/receipts/{id}/points", id.id()).contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(jsonPath("$.points").value(28))
    //             .andExpect(status().isOk());
    // }
}
