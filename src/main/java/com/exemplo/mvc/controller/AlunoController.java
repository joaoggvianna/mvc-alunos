// Aluno: João Gabriel Guedes Vianna
// RA: 00347671
// Descrição: Controller MVC que gerencia as rotas GET e POST de /alunos,
//            recebendo os dados do formulário e repassando a lista para a view.

package com.exemplo.mvc.controller;

import com.exemplo.mvc.model.Aluno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlunoController {

    private final List<Aluno> alunos = new ArrayList<>();

    @GetMapping("/alunos")
    public String mostrarFormulario() {
        String nomeView = "alunos-form";
        return nomeView;
    }

    @PostMapping("/alunos")
    public String cadastrarAluno(
            @RequestParam String nome,
            @RequestParam String matricula,
            Model model) {

        String nomeParam = nome;
        String matriculaParam = matricula;

        Aluno novoAluno = new Aluno(nomeParam, matriculaParam);
        alunos.add(novoAluno);
        model.addAttribute("alunos", alunos);

        String nomeView = "alunos-lista";
        return nomeView;
    }
}
