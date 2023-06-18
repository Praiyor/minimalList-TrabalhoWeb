package com.dsw.trabalho.minimalList.controller.api;

import com.dsw.trabalho.minimalList.dto.AchievementDTO;
import com.dsw.trabalho.minimalList.helper.HandleException;
import com.dsw.trabalho.minimalList.model.Achievement;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.repository.AchievementRepository;
import com.dsw.trabalho.minimalList.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievement")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid AchievementDTO achievementDTO){
        Achievement achievementModel = new Achievement();
        BeanUtils.copyProperties(achievementDTO, achievementModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(achievementRepository.save(achievementModel));
    }

    @GetMapping
    public ResponseEntity<List<Achievement>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(achievementRepository.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> listByUser(@PathVariable Integer idUser) throws HandleException {
        User user = userRepository.findById(idUser).orElseThrow(() -> new HandleException("Usuário não foi encontrado"));
        return ResponseEntity.status(HttpStatus.OK).body(achievementRepository.findByUser(user));
    }
}
