package com.dsw.trabalho.minimalList.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.trabalho.minimalList.dto.UserLibraryDTO;
import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.model.UserLibrary;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import com.dsw.trabalho.minimalList.repository.UserLibraryRepository;
import com.dsw.trabalho.minimalList.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class UserLibraryController {
    private final UserLibraryRepository repository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> findAllContentByUser(@PathVariable Integer idUser) {
        User user = userRepository.findById(idUser).orElseGet(null);
        if (user == null)  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
         
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByUser(user));
    }

    @GetMapping("/{idUser}/content/{idContent}")
    public ResponseEntity<Object> getOneByContent(@PathVariable Integer idUser, @PathVariable Integer idContent) {
        User user = userRepository.findById(idUser).orElseGet(null);
        if (user == null)  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByUserAndContent(user, contentRepository.findById(idContent).orElseGet(null)));
    }

    @PostMapping
    public ResponseEntity<Object> add(UserLibraryDTO libraryDTO) {
        User user = userRepository.findById(libraryDTO.getIdUser()).orElseGet(null);
        Content content = contentRepository.findById(libraryDTO.getIdContent()).orElseGet(null);
        if (user != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found!");
        if (content != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Content not found!");


        UserLibrary library = new UserLibrary();
        library.setUser(user);
        library.setContent(content);
        library.setEpisode(libraryDTO.getEpisode());
        library.setStatusCotent(libraryDTO.getStatusContent());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(library));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, UserLibraryDTO libraryDTO) {
        UserLibrary userLibrary = repository.findById(id).orElseGet(null);
        if (userLibrary == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserLibrary not found!");
        if  (userLibrary.getUser().getId() != libraryDTO.getIdUser() && userLibrary.getContent().getId() != libraryDTO.getIdContent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or Content not found!");
        }

        userLibrary.setStatusCotent(libraryDTO.getStatusContent());
        userLibrary.setEpisode(libraryDTO.getEpisode());

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(userLibrary));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        UserLibrary userLibrary = repository.findById(id).orElseGet(null);
        if (userLibrary == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserLibrary not found!");
        repository.delete(userLibrary);
        return ResponseEntity.status(HttpStatus.OK).body("UserLibrary deleted!");
    }

}
