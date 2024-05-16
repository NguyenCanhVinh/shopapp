package com.example.shopapp.controller;

import com.example.shopapp.Services.CategoryService;
import com.example.shopapp.dto.CategoryDTO;
import com.example.shopapp.models.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result){
        if (result.hasErrors()){

           List<String> errorMessage =result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
           return ResponseEntity.badRequest().body(errorMessage);
        }
        categoryService.createrCategory(categoryDTO);
        return ResponseEntity.ok("This is insertCategory"+ categoryDTO);
    }

    // Hien thi ta ca các category
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<Category> listCategory=categoryService.findAll();
        return  ResponseEntity.ok(listCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id,
                                               @Valid  @RequestBody  CategoryDTO categoryDTO){
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("update category"+ id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleteCategory id" + id);
    }
}
