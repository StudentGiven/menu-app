package com.chowrecipe.chowmenu.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.chowrecipe.chowmenu.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MenuRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public MenuRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    // Save a single menu
    public Menu save(Menu entity) {
        dynamoDBMapper.save(entity);
        return entity;
    }

    // Find a menu by ID
    public Optional<Menu> findById(String id) {
        Menu menu = dynamoDBMapper.load(Menu.class, id);
        return Optional.ofNullable(menu);
    }

    // Retrieve all menus
    public List<Menu> findAll() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Menu.class, scanExpression);
    }

    // Delete a menu by ID
    public void deleteById(String id) {
        Menu menu = dynamoDBMapper.load(Menu.class, id);
        if (menu != null) {
            dynamoDBMapper.delete(menu);
        }
    }

    // Delete a menu entity
    public void delete(Menu entity) {
        dynamoDBMapper.delete(entity);
    }
}
