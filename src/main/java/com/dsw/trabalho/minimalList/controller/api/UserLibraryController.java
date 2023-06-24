package com.dsw.trabalho.minimalList.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.trabalho.minimalList.dto.UserLibraryDTO;
import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.model.UserLibrary;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import com.dsw.trabalho.minimalList.repository.UserLibraryRepository;
import com.dsw.trabalho.minimalList.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class UserLibraryController {
    private final UserLibraryRepository repository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> findAllContentByUser(@PathVariable Integer idUser) throws HandleException {
        User user = userRepository.findById(idUser).orElseThrow(() -> new HandleException("Usuário não foi encontrado"));
         
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByUser(user));
    }

    @GetMapping("/{idUser}/content/{idContent}")
    public ResponseEntity<Object> getOneByContent(@PathVariable Integer idUser, @PathVariable Integer idContent)  throws HandleException {
        User user = userRepository.findById(idUser).orElseThrow(() -> new HandleException("Usuário não foi encontrado"));
        
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByUserAndContent(user, contentRepository.findById(idContent).orElse(null)));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid UserLibraryDTO libraryDTO) throws HandleException {
        User user = userRepository.findById(libraryDTO.getIdUser()).orElseThrow(() -> new HandleException("Usuário não foi encontrado"));
        Content content = contentRepository.findById(libraryDTO.getIdContent()).orElseThrow(() -> new HandleException("Conteudo não foi encontrado"));

        UserLibrary library = new UserLibrary();
        library.setUser(user);
        library.setContent(content);
        library.setEpisode(libraryDTO.getEpisode());
        library.setSeasonContent(libraryDTO.getSeasonContent());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(library));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody @Valid UserLibraryDTO libraryDTO) throws HandleException {
        UserLibrary userLibrary = repository.findById(id).orElseThrow(() -> new HandleException("Library não foi encontrada"));
        if  (userLibrary.getUser().getId() != libraryDTO.getIdUser() && userLibrary.getContent().getId() != libraryDTO.getIdContent()) {
            throw new HandleException("Usuário ou Conteudo não foi Encontrado!");
        }

        userLibrary.setSeasonContent(libraryDTO.getSeasonContent());
        userLibrary.setEpisode(libraryDTO.getEpisode());

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(userLibrary));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws HandleException {
        UserLibrary userLibrary = repository.findById(id).orElseThrow(() -> new HandleException("Library não foi encontrada"));
        repository.delete(userLibrary);
        return ResponseEntity.status(HttpStatus.OK).body(userLibrary);
    }

}
