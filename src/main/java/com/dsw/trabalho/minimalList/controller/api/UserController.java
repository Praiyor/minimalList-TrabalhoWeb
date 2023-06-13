package com.dsw.trabalho.minimalList.controller.api;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsw.trabalho.minimalList.dto.ProfileRequestDTO;
import com.dsw.trabalho.minimalList.dto.UserRegisterDTO;
import com.dsw.trabalho.minimalList.dto.UserSignInDTO;
import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.repository.UserRepository;
import com.dsw.trabalho.minimalList.service.FileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;
    private String pathUpload = "assets/images";

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable Integer id) throws HandleException {
        User user = repository.findById(id).orElseThrow(() -> new HandleException("User not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody UserRegisterDTO registerDTO,
            HttpServletRequest request,
            HttpServletResponse response) throws HandleException {
        Optional<User> user = repository.findByEmail(registerDTO.getEmail());
        if (user.isPresent()) throw new HandleException("User already exists!");

        User newUser = new User();
        newUser.setEmail(registerDTO.getEmail());
        newUser.setNickname(registerDTO.getNickname());
        newUser.setPassword(registerDTO.getPassword());

        return ResponseEntity.ok(repository.save(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody UserSignInDTO userDto) throws HandleException {
        User user = repository.findByEmail(userDto.getEmail()).orElseThrow(() -> new HandleException("Email or password incorrect"));
        if (!user.getPassword().equals(userDto.getPassword())) 
            throw new HandleException("Email or password incorrect");

        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Object> updateProfile(
            @PathVariable(value = "id") Integer id, @RequestBody ProfileRequestDTO profileDto) throws HandleException {
        User user = repository.findById(id).orElseThrow(() -> new HandleException("User not found"));

        BeanUtils.copyProperties(profileDto, user);
        user.setId(id);
        repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/profile/image/{id}")
    public ResponseEntity<Object> updateProfileImage(
            @PathVariable Integer id, @RequestParam("image") MultipartFile file) throws HandleException {
        User user= repository.findById(id).orElseThrow(() -> new HandleException("User not found"));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String randomID = UUID.randomUUID().toString();
        fileName = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        String uploadDir = pathUpload + "/profile/" + user.getId();

        try {

            FileService.saveFile(uploadDir, fileName, file);

            user.setImage(fileName);
            user.setImagePath(uploadDir);
            repository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Could not save image: " + fileName);
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/profile/background/{id}")
    public ResponseEntity<Object> updateProfileBackground(
            @PathVariable Integer id, @RequestParam("image") MultipartFile file) throws HandleException {
        User user = repository.findById(id).orElseThrow(() -> new HandleException("User not found"));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String randomID = UUID.randomUUID().toString();
        fileName = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        String uploadDir = pathUpload + "/profile/" + user.getId();

        try {

            FileService.saveFile(uploadDir, fileName, file);
            user.setBackground(fileName);
            user.setImagePath(uploadDir);
            repository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Could not save image: " + fileName);
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<Object> deleteProfile(@PathVariable(value = "id") Integer id) throws HandleException {
        User user = repository.findById(id).orElseThrow(() -> new HandleException("User not found!"));
        repository.deleteById(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted!");
    }
}
