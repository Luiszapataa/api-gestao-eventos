# API de Gestão de Eventos e Inscrições

API RESTful em Java com Spring Boot para gerenciar eventos, participantes e inscrições.

## O que a API faz

Gerencia Eventos, Participantes e Inscrições. Cada evento tem um número limitado de vagas, participantes se inscrevem nos eventos, e a API bloqueia inscrições quando as vagas acabam.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL
- Bean Validation
- Maven
- HTML, CSS e JavaScript (front-end simples para testar a API visualmente)

## Estrutura do código

```
controller/  → recebe as requisições HTTP
service/     → regras de negócio e conversão entre DTOs e entidades
repository/  → acesso ao banco de dados (Spring Data JPA)
model/       → entidades JPA
dto/         → formatos de entrada e saída da API
exception/   → exceções customizadas e tratamento global de erros
```

O Controller não trabalha com as entidades diretamente, só com DTOs. Isso evita expor a estrutura interna do banco na API.

## Modelo de dados

- **Evento**: nome, descrição, data, local, quantidade de vagas
- **Participante**: nome e e-mail
- **Inscricao**: liga um Evento a um Participante, com a data em que a inscrição foi feita

```
Evento (1) ────── (N) Inscricao (N) ────── (1) Participante
```

## Regras de negócio

- Inscrição bloqueada se o evento já atingu o limite de vagas
- E-mail de participante não pode se repetir
- Data da inscrição é preenchida automaticamente

## Tratamento de erros

Um manipulador global (`@RestControllerAdvice`) padroniza as respostas de erro:

```json
{
  "timestamp": "2026-09-19T20:15:00",
  "status": 404,
  "mensagem": "Evento não encontrado com id: 99"
}
```

| Situação | Status HTTP |
|---|---|
| Recurso não encontrado | 404 |
| Vagas esgotadas | 400 |
| E-mail já cadastrado | 400 |
| Criado com sucesso | 201 |
| Excluído com sucesso | 204 |

## Endpoints

### Eventos
| Método | Rota | Descrição |
|---|---|---|
| POST | `/eventos` | Cria um evento |
| GET | `/eventos` | Lista todos |
| GET | `/eventos/{id}` | Busca por id |
| PUT | `/eventos/{id}` | Atualiza |
| DELETE | `/eventos/{id}` | Remove |

### Participantes
| Método | Rota | Descrição |
|---|---|---|
| POST | `/participantes` | Cria um participante |
| GET | `/participantes` | Lista todos |
| GET | `/participantes/{id}` | Busca por id |
| DELETE | `/participantes/{id}` | Remove |

### Inscrições
| Método | Rota | Descrição |
|---|---|---|
| POST | `/inscricoes` | Inscreve um participante em um evento |
| GET | `/inscricoes` | Lista todas |
| GET | `/inscricoes/evento/{eventoId}` | Inscrições de um evento |
| GET | `/inscricoes/participante/{participanteId}` | Histórico de um participante |
| GET | `/inscricoes/{id}` | Busca por id |
| DELETE | `/inscricoes/{id}` | Remove |
## Como rodar

Pré-requisitos: Java 17+, Maven, PostgreSQL.

1. Clone o repositório:
```bash
git clone https://github.com/Luiszapataa/api-gestao-eventos.git
```

2. Crie o banco:
```sql
CREATE DATABASE gestao_eventos;
```

3. Configure a variável de ambient `DB_PASSWORD` com a senha do seu PostgreSQL.

4. Rode a aplicação:
```bash
mvn spring-boot:run
```

5. Acesse `http://localhost:8080`.

## Front-end

Página simples em HTML, CSS e JavaScript puro, servida pelo Spring Boot como arquivo estático (`src/main/resources/static/index.html`). Abre automaticamente em `http://localhost:8080` e cobre o CRUD completo de Eventos, Participantes e Inscrições.