# Cadastro de Alunos — Spring Boot MVC

**Disciplina:** Arquitetura de Software — PUC-SP
**Aluno:** João Gabriel Guedes Vianna
**RA:** 00347671

---

## Sobre o Projeto

Aplicação web para cadastro e listagem de alunos, desenvolvida em Java com **Spring Boot** e **Thymeleaf**. O objetivo é demonstrar na prática o padrão arquitetural **MVC (Model-View-Controller)**, separando de forma clara as responsabilidades de dados, apresentação e controle de fluxo.

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Papel no Projeto |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.2.5 | Framework web e gerenciamento de dependências |
| Spring MVC | (incluso) | Implementação do padrão MVC via anotações |
| Thymeleaf | (incluso) | Motor de templates para renderização das Views |
| Maven | 3.x | Build e gerenciamento de dependências |

---

## Arquitetura MVC

O padrão **MVC** organiza a aplicação em três camadas independentes que se comunicam de forma unidirecional:

```
         ┌─────────────────────────────────┐
         │           NAVEGADOR             │
         └──────────┬──────────────────────┘
                    │  requisição HTTP
                    ▼
         ┌─────────────────────────────────┐
         │          CONTROLLER             │
         │      AlunoController.java       │
         │                                 │
         │  Recebe a requisição, aciona    │
         │  o Model e seleciona a View     │
         └────────┬────────────┬───────────┘
                  │            │
         aciona   │            │  envia dados
                  ▼            ▼
      ┌──────────────┐  ┌─────────────────────┐
      │    MODEL     │  │        VIEW         │
      │  Aluno.java  │  │  alunos-form.html   │
      │              │  │  alunos-lista.html  │
      │  Representa  │  │                     │
      │  os dados e  │  │  Renderiza o HTML   │
      │  valida as   │  │  com Thymeleaf,     │
      │  regras de   │  │  sem lógica de      │
      │  negócio     │  │  negócio            │
      └──────────────┘  └─────────────────────┘
```

### Model — `src/main/java/com/exemplo/mvc/model/`

Representa os **dados e as regras de negócio**. Não tem conhecimento da View nem do Controller.

- **`Aluno.java`** — Entidade que encapsula os atributos `nome` e `matricula`. O construtor valida que o nome não seja nulo ou vazio, lançando `IllegalArgumentException` caso a regra seja violada.

### View — `src/main/resources/templates/`

Responsável pela **apresentação**. Usa Thymeleaf para inserir dados dinamicamente no HTML. Não contém lógica de negócio.

- **`alunos-form.html`** — Formulário de cadastro com os campos nome e matrícula.
- **`alunos-lista.html`** — Tabela com todos os alunos cadastrados, gerada via `th:each`.

### Controller — `src/main/java/com/exemplo/mvc/controller/`

**Orquestra** o fluxo da aplicação. Recebe requisições HTTP, usa o Model e decide qual View retornar.

- **`AlunoController.java`**
  - `GET /alunos` → retorna o formulário de cadastro
  - `POST /alunos` → cria um `Aluno`, adiciona à lista em memória e retorna a View de listagem

---

## Fluxo de uma Requisição

```
Navegador
   │
   ├─ GET /alunos ─────────────────────► AlunoController
   │                                          │
   │                                    retorna "alunos-form"
   │                                          │
   │◄──────────────────── Thymeleaf renderiza alunos-form.html
   │
   ├─ POST /alunos (nome, matricula) ──► AlunoController
   │                                          │
   │                                    new Aluno(nome, matricula)  ──► Model
   │                                          │
   │                                    alunos.add(novoAluno)
   │                                          │
   │                                    retorna "alunos-lista" + lista
   │                                          │
   │◄──────────────────── Thymeleaf renderiza alunos-lista.html
```

---

## Estrutura de Arquivos

```
mvc-alunos/
├── pom.xml                                     # Dependências e configuração de build
├── README.md
└── src/
    └── main/
        ├── java/com/exemplo/mvc/
        │   ├── MvcApplication.java             # Ponto de entrada da aplicação
        │   ├── model/
        │   │   └── Aluno.java                  # Model: entidade com validação
        │   └── controller/
        │       └── AlunoController.java        # Controller: coordena Model e View
        └── resources/
            ├── application.properties          # Configurações (porta: 8080)
            └── templates/
                ├── alunos-form.html            # View: formulário de cadastro
                └── alunos-lista.html           # View: listagem de alunos
```

---

## Como Executar

**Pré-requisitos:** Java 17+ e Maven instalados.

```bash
# 1. Clone o repositório
git clone <url-do-repositorio>
cd mvc-alunos

# 2. Execute a aplicação
mvn spring-boot:run
```

Acesse no navegador: **http://localhost:8080/alunos**

---

## Observações

- Os dados são armazenados **em memória** (lista Java). Ao reiniciar a aplicação os cadastros são perdidos — não há banco de dados, mantendo o foco na demonstração do padrão MVC.
