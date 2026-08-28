# 💈 API de Agendamento para Barbearia
Aplicação full-stack para gerenciamento de uma barbearia: interface web + API REST + banco de dados, com a regra de negócio que **impede dois agendamentos no mesmo horário para o mesmo profissional**.

## 🔗 Demo ao vivo

**App no ar:** https://agendamento-m283.onrender.com

Abra o link e use a barbearia direto na tela: cadastre clientes e profissionais, marque agendamentos e veja a regra de horário ocupado funcionando na interface.

> ⏳ O primeiro acesso pode levar ~40s — no plano gratuito o servidor "dorme" quando fica parado.

API REST para gerenciamento de agendamentos de uma barbearia, com regra de negócio que **impede o agendamento de dois horários iguais para o mesmo profissional**. Projeto desenvolvido para portfólio, com foco em boas práticas de arquitetura, modelagem de banco relacional e testes automatizados.

---

## 📋 Sobre o projeto

O sistema permite cadastrar clientes, profissionais (barbeiros) e agendamentos. O diferencial está na **camada de regra de negócio**: antes de salvar um agendamento, a aplicação verifica se aquele profissional já possui um horário marcado no mesmo momento — e, se tiver, recusa a operação com uma resposta HTTP clara.

A arquitetura segue a separação clássica em camadas:

- **Controller** — recebe as requisições HTTP e devolve as respostas (JSON).
- **Service** — concentra a regra de negócio (a validação de horário ocupado).
- **Repository** — acesso ao banco de dados via Spring Data JPA.
- **Model (Entidades)** — mapeamento objeto-relacional das tabelas.

---

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 4.1**
- **Spring Web** — construção da API REST
- **Spring Data JPA / Hibernate** — persistência
- **H2 Database** — banco em memória para desenvolvimento
- **Bean Validation** — validação de dados
- **JUnit 5** — testes automatizados
- **Maven** — build e gerenciamento de dependências
- - **Frontend:** HTML, CSS e JavaScript (sem framework)

---

## 🗄️ Modelagem

O banco possui três entidades principais com relacionamento **um-para-muitos**:

```
CLIENTE (1) ──< (N) AGENDAMENTO (N) >── (1) PROFISSIONAL
```

- Um **Cliente** pode ter vários agendamentos.
- Um **Profissional** pode ter vários agendamentos.
- Cada **Agendamento** pertence a um cliente e a um profissional, e guarda `dataHora` e `status`.

---

## ⭐ Regra de negócio

> Um mesmo profissional não pode ter dois agendamentos no mesmo horário.

A validação é feita na camada de serviço. Quando violada, a API retorna **HTTP 400 (Bad Request)** no formato padronizado *ProblemDetail* (RFC 7807):

```json
{
  "title": "Bad Request",
  "status": 400,
  "detail": "O profissional já está ocupado nesse horário.",
  "instance": "/agendamentos"
}
```

---

## 🚀 Como rodar

**Pré-requisitos:** Java 21 instalado.

```bash
# Clone o repositório
git clone https://github.com/SEU-USUARIO/agendamento.git
cd agendamento

# Rode a aplicação (Windows)
.\mvnw.cmd spring-boot:run

# Rode a aplicação (Linux/Mac)
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

---

## 📡 Endpoints

| Método | Rota              | Descrição                        |
|--------|-------------------|----------------------------------|
| POST   | `/clientes`       | Cadastra um cliente              |
| GET    | `/clientes`       | Lista todos os clientes          |
| POST   | `/profissionais`  | Cadastra um profissional         |
| GET    | `/profissionais`  | Lista todos os profissionais     |
| POST   | `/agendamentos`   | Cria um agendamento (aplica a regra) |
| GET    | `/agendamentos`   | Lista todos os agendamentos      |

### Exemplo — criar um agendamento

```http
POST http://localhost:8080/agendamentos
Content-Type: application/json

{
  "dataHora": "2026-08-25T14:00:00",
  "status": "AGENDADO",
  "cliente": { "id": 1 },
  "profissional": { "id": 1 }
}
```

---

## 🧪 Testes

O projeto inclui teste automatizado que garante o funcionamento da regra de horário ocupado.

```bash
.\mvnw.cmd test
```

---

## 🗺️ Próximos passos (roadmap)

Melhorias planejadas para evoluir de um MVP para um produto real:

- [ ] **Serviço com duração** — considerar o tempo de cada corte/barba para evitar sobreposição de horários.
- [ ] **Horário de funcionamento** — impedir agendamentos fora do expediente da barbearia.
- [ ] **Autenticação** — login com Spring Security + JWT.
- [ ] **Notificações** — lembrete de agendamento por e-mail/WhatsApp.
- [ ] **Persistência real** — trocar o H2 em memória por PostgreSQL.
- [ ] **Documentação interativa** — Swagger/OpenAPI.
- [ ] **Deploy** — publicar em nuvem (Render).

---

## 👤 Autor

**Anderson Souza**
Estudante de Análise e Desenvolvimento de Sistemas.

- GitHub: [@shiionn21](https://github.com/shiionn21)
- LinkedIn: [Anderson Souza](www.linkedin.com/in/dev-andersonn)
