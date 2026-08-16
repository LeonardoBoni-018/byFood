# byFood

API de restaurante fixo (estilo iFood) para gerenciar cardápio e pedidos, com confirmação via WhatsApp. Construída com Spring Boot 4.

## Fases do projeto

- **Fase 0 — Fundação** (concluída): scaffold do projeto, domínio base (Restaurant, MenuItem), migrations Flyway, perfis dev/prod, Docker, testes de integração.
- **Fase 1 — Cardápio + Autenticação** (em andamento): usuário admin, login JWT, CRUD do cardápio e endpoints públicos.
- **Fase 2 — Pedidos**: criação e gestão de pedidos.
- **Fase 3 — WhatsApp**: confirmação de pedidos via Evolution API.
- **Fase 4 — Pagamentos**: a definir (Mercado Pago como candidato).

## Stack

- Java 17, Spring Boot 4.1.0, Maven
- Spring Data JPA + PostgreSQL
- Flyway (schema versionado)
- Spring Security + JWT (jjwt 0.12.6)
- Springdoc OpenAPI (Swagger UI)
- Testcontainers (testes de integração)
- Docker + Docker Compose

## Estrutura

```
byfood/
├── docker-compose.yml
├── Dockerfile
└── src/
    ├── main/
    │   ├── java/com/byfood/api/
    │   │   ├── common/BaseEntity.java
    │   │   ├── config/         # SecurityConfig, JwtService, JpaAuditingConfig
    │   │   ├── controller/     # AuthController, RestaurantController
    │   │   ├── dto/            # Records de request/response
    │   │   ├── exception/      # NotFoundException, ConflictException, GlobalExceptionHandler
    │   │   ├── mapper/
    │   │   ├── model/          # Restaurant, MenuItem, AdminUser
    │   │   ├── repository/
    │   │   └── service/        # AuthService, AdminUserDetailsService, RestaurantService
    │   └── resources/
    │       ├── application.yaml / application-dev.yaml / application-prod.yaml
    │       └── db/migration/   # V1__init.sql, V2__admin_user.sql
    └── test/java/com/byfood/api/
```

## Endpoints atuais

| Método | Rota                | Acesso | Descrição |
|--------|---------------------|--------|-----------|
| GET    | `/public/restaurant` | público | Dados do restaurante |
| POST   | `/auth/login`        | público | Login admin, retorna JWT |

## Variáveis de ambiente

| Variável         | Dev (default)                                 | Prod (obrigatório)              |
|------------------|------------------------------------------------|---------------------------------|
| `DB_URL`         | `jdbc:postgresql://localhost:5432/byfood`      | URL do banco                    |
| `DB_USERNAME`    | `postgres`                                     | usuário do banco                |
| `DB_PASSWORD`    | `123456`                                       | senha do banco                  |
| `JWT_SECRET`     | `byfood-dev-secret-key-change-in-production-123456` | secret **≥ 32 bytes** (HS256) |
| `JWT_EXPIRATION` | `86400000` (24h em ms)                         | expiração em ms                 |

> O profile `prod` falha ao subir se qualquer variável obrigatória não for definida.

## Usuário admin inicial (dev)

- Usuário: `admin`
- Senha: `admin123`

## Como rodar

### Local (dev)

```bash
cd byfood
.\mvnw.cmd spring-boot:run
# profile default usa Testcontainers? Não: rode com -Dspring-boot.run.profiles=dev
```

Com Docker Compose:

```bash
docker compose up --build -d
```

### Testes

```bash
.\mvnw.cmd clean verify
```

Requer Docker Desktop rodando (Testcontainers).

### Swagger UI

- API docs: `http://localhost:8080/v3/api-docs`
- UI: `http://localhost:8080/swagger-ui.html`

## Migrations

| Versão | Conteúdo                                  |
|--------|-------------------------------------------|
| V1     | Tabelas `restaurant` e `menu_item` + seed |
| V2     | Tabela `admin_user` + seed admin          |

## Status atual da Fase 1

- [x] Passo 1 — AdminUser + migration V2
- [x] Passo 2 — Dependência JWT + config
- [x] Passo 3 — JwtService + beans de segurança
- [x] Passo 4 — Endpoint de login
- [ ] Passo 5 — JwtAuthenticationFilter + proteger `/admin/**`
- [ ] Passo 6 — Cardápio público (`GET /public/menu`)
- [ ] Passo 7 — CRUD admin (`/admin/menu`)
- [ ] Passo 8 — Testes da Fase 1
