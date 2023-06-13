package com.dsw.trabalho.minimalList.controller.api;

import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentRepository contentRepository;

    @GetMapping
    public ResponseEntity<List<Content>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) throws HandleException {
        Content optionalContent = contentRepository.findById(id).orElseThrow(() -> new HandleException("Content not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(optionalContent);
    }

    @GetMapping("/{search}")
    public ResponseEntity<Object> searchByName(@PathVariable String search){
        List<Content> content = contentRepository.findAllByNameOrTitle(search);
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("/getSeason")
    public ResponseEntity<Object> findAllSeason(){
        List<Integer> seasons = contentRepository.findAllSeason();

        if (seasons == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seasons not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(seasons);
    }

    @GetMapping("/findBySeason/{season}")
    public ResponseEntity<Object> findAllBySeason(@PathVariable int season){
        List<Content> content = contentRepository.findAllBySeason(season);
        if (content == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("/{category}")
    public ResponseEntity<Object> findAllByCategory(@PathVariable int category){
        List<Content> contents = contentRepository.findAllByCategory(category);

        if (contents == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not Found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(contents);
    }
}
