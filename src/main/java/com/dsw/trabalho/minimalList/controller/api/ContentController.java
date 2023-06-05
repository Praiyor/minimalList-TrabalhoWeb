package com.dsw.trabalho.minimalList.controller.api;

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
    public ResponseEntity<Object> getOne(@PathVariable Integer id){
        Content optionalContent = contentRepository.findById(id).orElse(null);
        if(optionalContent == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");

        return ResponseEntity.status(HttpStatus.OK).body(optionalContent);
    }

    @GetMapping("/{search}")
    public ResponseEntity<Object> searchByName(@PathVariable String search){
        List<Content> content = contentRepository.findAllByNameOrTitle(search);
        System.out.println("breaking");
        if (content == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(content);
    }
}
