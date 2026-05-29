// Aluno: João Gabriel Guedes Vianna
// RA: 00347671
// Descrição: Modelo (entidade) que representa um aluno com os atributos nome e matrícula.

package com.exemplo.mvc.model;

public class Aluno {

    private String nome;
    private String matricula;

    public Aluno(String nome, String matricula) {
        boolean nomeInvalido = nome == null || nome.trim().isEmpty();
        if (nomeInvalido) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        String resultado = this.nome;
        return resultado;
    }

    public String getMatricula() {
        String resultado = this.matricula;
        return resultado;
    }
}
