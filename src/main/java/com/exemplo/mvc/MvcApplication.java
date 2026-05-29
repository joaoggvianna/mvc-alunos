// Aluno: João Gabriel Guedes Vianna
// RA: 00347671
// Descrição: Classe principal da aplicação Spring Boot. Ponto de entrada que inicializa o contexto do servidor.

package com.exemplo.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }
}
