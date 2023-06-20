package com.dsw.trabalho.minimalList.controller.api;

import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.Category;
import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.repository.CategoryRepository;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Content>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) throws HandleException {
        Content optionalContent = contentRepository.findById(id).orElseThrow(() -> new HandleException("Conteudo não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(optionalContent);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchByName(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer season,
        @RequestParam(required = false) Integer category
    ) {
        System.out.println(title + " " + name + " " + season + " " + category);
        List<Content> content = contentRepository.searchContent(title, name, season, category);
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("/getSeason")
    public ResponseEntity<Object> findAllSeason() {
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findAllSeason());
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<Object> findAllBySeason(@PathVariable int season) {
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findAllBySeason(season));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Object> findAllByCategory(@PathVariable Integer categoryId) throws HandleException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new HandleException("Categoria não foi encontrada!"));
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findAllByCategory(category));
    }
}
