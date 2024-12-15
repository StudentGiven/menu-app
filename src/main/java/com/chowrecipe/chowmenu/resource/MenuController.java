package com.chowrecipe.chowmenu.resource;

import com.chowrecipe.chowmenu.model.Menu;
import com.chowrecipe.chowmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

//    @GetMapping
//    public ResponseEntity<List<Menu>> getAll() {
//        List<Menu> menus = menuService.getAllMenus();
//        return ResponseEntity.ok(menus);
//    }

    // GET /menus - Return all menus
    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }


    // GET /menu/{menuId} - Return details of one menu
    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable String menuId) {
        Menu menu = menuService.getMenuById(menuId);
        if (menu != null) {
            return ResponseEntity.ok(menu);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // POST /menus - Add a new menu
    @PostMapping
    public ResponseEntity<String> addMenu(@RequestBody Menu menu) {
        menuService.addMenu(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body("Menu added successfully");
    }

//    // DELETE /menu/{menuId} - Delete a menu by ID
//    @DeleteMapping("/{menuId}")
//    public ResponseEntity<String> deleteMenu(@PathVariable String menuId) {
//        boolean isDeleted = menuService.deleteMenu(menuId);
//        if (isDeleted) {
//            return ResponseEntity.ok("Menu deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu not found");
//        }
//    }
}
