package com.dsw.trabalho.minimalList.controller.api;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Object> getOne(@PathVariable UUID id){
        Optional<Content> optionalContent = contentRepository.findById(id);

        if(!optionalContent.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalContent.get());
    }
}
