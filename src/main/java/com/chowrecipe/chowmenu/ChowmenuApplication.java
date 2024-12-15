package com.chowrecipe.chowmenu;

import com.chowrecipe.chowmenu.model.Category;
import com.chowrecipe.chowmenu.model.Menu;
import com.chowrecipe.chowmenu.service.DynamoDBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChowmenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChowmenuApplication.class, args);
        System.out.println("Hello World!");
        System.out.println("Hello World! test");

        System.out.println("This is a test");
        System.out.println("AWS Region: " + System.getenv("AWS_REGION"));
        System.out.println("AWS Access Key ID: " + System.getenv("AWS_ACCESS_KEY_ID"));
        System.out.println("AWS SECRET ACCESS KEY: " + System.getenv("AWS_SECRET_ACCESS_KEY"));

        DynamoDBService dynamoDBService = new DynamoDBService();
        dynamoDBService.createTableIfNotExists();

        System.out.println("This is a test2");
        System.out.println("AWS Region: " + System.getenv("AWS_REGION"));
        System.out.println("AWS Access Key ID: " + System.getenv("AWS_ACCESS_KEY_ID"));

        Menu menu = new Menu();
        menu.setMenuId("100001");
        menu.setMenuName("水煮牛肉");
        menu.setDescription("水煮牛肉是一道经典川菜，以鲜嫩的牛肉片为主料，搭配麻辣鲜香的汤汁，辅以丰富的蔬菜，香辣可口，回味无穷。");
        menu.setCategory(Category.BEEF_LAMB);

        // Save the item to the DynamoDB table
        dynamoDBService.saveItem(menu);

        System.out.println("This is a test3");

    }

}
