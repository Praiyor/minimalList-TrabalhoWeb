package com.dsw.trabalho.minimalList.controller.api;


import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.Category;
import com.dsw.trabalho.minimalList.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Object> listByName(@PathVariable String name) throws HandleException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findByName(name));
    }
}
