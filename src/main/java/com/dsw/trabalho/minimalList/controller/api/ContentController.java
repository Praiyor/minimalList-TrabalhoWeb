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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id){
        Optional<Content> optionalContent = contentRepository.findById(id);

        if(!optionalContent.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalContent.get());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> searchByName(@PathVariable String name){
        List<Content> content = contentRepository.findByNameLike(name);
        if (content == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(content);
    }
}
