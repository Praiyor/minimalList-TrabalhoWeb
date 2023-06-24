package com.dsw.trabalho.minimalList.controller.api;

import com.dsw.trabalho.minimalList.dto.ReviewRequestDTO;
import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.Review;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import com.dsw.trabalho.minimalList.repository.ReviewRepository;
import com.dsw.trabalho.minimalList.repository.UserRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> findAllReviewsByUser(@PathVariable Integer userId)
            throws HandleException {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new HandleException("Usuário não foi encontrado!"));
        List<Review> reviews = repository.findAllReviewsByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<Object> findAllReviewsByContent(@PathVariable Integer contentId)
            throws HandleException {
        Content content = contentRepository
                .findById(contentId)
                .orElseThrow(() -> new HandleException("Conteudo não foi encontrado!"));
        List<Review> review = repository.findAllByContent(content);

        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Integer id, @RequestBody @Valid ReviewRequestDTO reviewDto)
            throws HandleException {
        Review review = repository
                .findById(id)
                .orElseThrow(() -> new HandleException("Review não foi encontrada!"));
        Review reviewVerify = repository.findByUserAndContent(reviewDto.getIdUser(), reviewDto.getIdContent());

        if (review != reviewVerify)
            throw new HandleException("Não é possível alterar review");

        review.setRate(reviewDto.getRate());
        review.setText(reviewDto.getText());
        review.setTitle(reviewDto.getTitle());
        review.setSpollier(reviewDto.isSpollier());

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
        Optional<Review> review = repository.findById(id);

        if (!review.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Review não deletado!");
        }
        repository.delete(review.get());
        return ResponseEntity.status(HttpStatus.OK).body("Review deletada!");
    }

    @PostMapping
    public ResponseEntity<Object> addReview(@RequestBody @Valid ReviewRequestDTO reviewDto)
            throws HandleException {
        User user = userRepository
                .findById(reviewDto.getIdUser())
                .orElseThrow(() -> new HandleException("Usuário não foi encontrado!"));
        Content content = contentRepository
                .findById(reviewDto.getIdContent())
                .orElseThrow(() -> new HandleException("Conteudo não foi encontrado!"));
        Review reviewExist = repository.findByUserAndContent(reviewDto.getIdUser(), reviewDto.getIdContent());

        Review review = null;
        if (reviewExist == null) {
            review = new Review();
        } else {
            review = reviewExist;
        }

        review.setContent(content);
        review.setUser(user);
        review.setRate(reviewDto.getRate());
        review.setText(reviewDto.getText());
        review.setSpollier(reviewDto.isSpollier());
        review.setTitle(reviewDto.getTitle());
        repository.save(review);

        return ResponseEntity.status(HttpStatus.OK).body(review);
    }
}
