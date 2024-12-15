package com.chowrecipe.chowmenu.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;

import com.chowrecipe.chowmenu.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoDBService {

    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB amazonDynamoDB;

    @Autowired
    public DynamoDBService() {
        // Initialize the AmazonDynamoDB client and DynamoDBMapper
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-east-1") // Replace with your region
                .build();
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void createTableIfNotExists() {
        // Generate the CreateTableRequest for the Item class
        CreateTableRequest request = dynamoDBMapper.generateCreateTableRequest(Menu.class)
                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Set the read and write capacity

        // Check if the table exists
        if (!amazonDynamoDB.listTables().getTableNames().contains("Menus")) {
            amazonDynamoDB.createTable(request);
            System.out.println("Table 'Menus' created successfully.");
        } else {
            System.out.println("Table 'Menus' already exists.");
        }
    }

//    public void createOrderTableIfNotExists() {
//        // Generate the CreateTableRequest for the Order class
//        CreateTableRequest request = dynamoDBMapper.generateCreateTableRequest(Order.class)
//                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Set the read and write capacity
//
//        // Add GSI for orderId
//        GlobalSecondaryIndex orderIdIndex = new GlobalSecondaryIndex()
//                .withIndexName("OrderIdIndex") // Name of the GSI
//                .withKeySchema(
//                        new KeySchemaElement("orderId", KeyType.HASH) // GSI Partition Key
//                )
//                .withProjection(new Projection().withProjectionType(ProjectionType.ALL))
//                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Include all attributes
//
//        // Attach the GSI to the CreateTableRequest
//        request.withGlobalSecondaryIndexes(orderIdIndex);
//
//        // Check if the table exists
//        if (!amazonDynamoDB.listTables().getTableNames().contains("Orders")) {
//            amazonDynamoDB.createTable(request);
//            System.out.println("Table 'Orders' created successfully.");
//        } else {
//            System.out.println("Table 'Orders' already exists.");
//        }
//    }

    // Method to save an item
    public void saveItem(Menu menu) {
        dynamoDBMapper.save(menu);
        System.out.println("Menu saved successfully: " + menu.getMenuId() + ": " + menu.getMenuName());
    }

//    // Method to save an order
//    public void saveOrder(Order order) {
//        dynamoDBMapper.save(order);
//        System.out.println("Order saved successfully: " + order.getOrderId());
//    }
}
