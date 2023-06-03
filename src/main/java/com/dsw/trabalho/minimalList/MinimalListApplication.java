package com.dsw.trabalho.minimalList;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.apache.bcel.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.repository.ContentRepository;

@SpringBootApplication
public class MinimalListApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinimalListApplication.class, args);
	}
}
