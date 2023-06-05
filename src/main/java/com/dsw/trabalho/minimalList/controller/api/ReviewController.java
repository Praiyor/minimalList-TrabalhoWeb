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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> findAllReviewsByUser(@PathVariable Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        User user = userOptional.get();
        List<Review> reviews = repository.findAllReviewsByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<Object> findAllReviewsByContent(@PathVariable Integer contentId) {
        Content content = contentRepository.findById(contentId).orElse(null);
        List<Review> review = repository.findAllByContent(content);

        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Integer id, @RequestBody @Valid ReviewRequestDTO reviewDto)
            throws HandleException {
        Review review = repository.findById(id).orElseThrow(() -> new HandleException("Review not found!"));
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
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted!");
    }

    @PostMapping
    public ResponseEntity<Object> addReview(@RequestBody @Valid ReviewRequestDTO reviewDto) {
        Optional<User> userExist = userRepository.findById(reviewDto.getIdUser());
        Optional<Content> contentExist = contentRepository.findById(reviewDto.getIdContent());
        if (!userExist.isPresent())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized!");

        if (!contentExist.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found!");

        User user = userExist.get();
        Content content = contentExist.get();

        Optional<Review> reviewExists = repository.findByUserAndContent(user, content);
        if (reviewExists.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Review já existe");
        }

        Review review = new Review();
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
