package com.chowrecipe.chowmenu.service;

import com.chowrecipe.chowmenu.dao.MenuRepository;
import com.chowrecipe.chowmenu.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // Retrieve all menus
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        menuRepository.findAll().forEach(menus::add);
        return menus;
    }

    // Retrieve a single menu by ID
    public Menu getMenuById(String menuId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        return menu.orElse(null);
    }

    // Add a new menu
    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

//    // Delete a menu by ID
//    public boolean deleteMenu(String menuId) {
//        if (menuRepository.existsById(menuId)) {
//            menuRepository.deleteById(menuId);
//            return true;
//        }
//        return false;
//    }
}
