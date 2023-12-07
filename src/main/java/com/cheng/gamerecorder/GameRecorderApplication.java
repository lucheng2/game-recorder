package com.cheng.gamerecorder;

import com.cheng.gamerecorder.repository.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
@EnableJpaAuditing
public class GameRecorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameRecorderApplication.class, args);
	}

}
