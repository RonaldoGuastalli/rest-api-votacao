package br.com.rjguastalli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SessaoVotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessaoVotacaoApplication.class, args);
    }

}
