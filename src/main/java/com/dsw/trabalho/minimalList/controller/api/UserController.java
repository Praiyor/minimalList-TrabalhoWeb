package com.dsw.trabalho.minimalList.controller.api;

import com.dsw.trabalho.minimalList.dto.ProfileRequestDTO;
import com.dsw.trabalho.minimalList.dto.UserRegisterDTO;
import com.dsw.trabalho.minimalList.dto.UserSignInDTO;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterDTO registerDTO) {
        Optional<User> user = repository.findByEmail(registerDTO.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists!");
        }

        User newUser = User.builder().email(registerDTO.getEmail()).password(registerDTO.getPassword()).build();
        repository.save(newUser);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody UserSignInDTO userDto) {
        Optional<User> user = repository.findByEmail(userDto.getEmail());

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email or password incorrect");
        }

        if (!user.get().getPassword().equals(userDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email or password incorrect");
        }

        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Object> updateProfile(
            @PathVariable(value = "id") Integer id, @RequestBody ProfileRequestDTO profileDto) {
        Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        User user = userOptional.get();

        BeanUtils.copyProperties(profileDto, user);
        user.setId(id);
        repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
