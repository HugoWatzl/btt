# 🥋 Sistema de Gestão da Academia BTT

## 📖 Descrição

O Sistema de Gestão da Academia BTT é uma API REST desenvolvida em Java utilizando Spring Boot para gerenciamento de alunos, professores, modalidades e aulas de uma academia de artes marciais.

O sistema permite realizar operações de cadastro, consulta, atualização e remoção de dados através de endpoints REST documentados com Swagger/OpenAPI.

Além do CRUD completo, o projeto implementa padrões de projeto (Design Patterns), relacionamentos entre entidades e regras de negócio específicas da academia.

---

# 🚀 Tecnologias Utilizadas

## Backend

* Java 17
* Spring Boot
* Spring Web
* Maven

## Banco de Dados

* MySQL

## Documentação

* Swagger / OpenAPI

## Bibliotecas

* Lombok

## Dependências Utilizadas

### Spring Web

Responsável pela criação da API REST.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### Lombok

Redução de código boilerplate.

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

### Spring Boot DevTools

Auxilia o desenvolvimento com reinicialização automática da aplicação.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

### Swagger/OpenAPI

Documentação automática da API.

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.17</version>
</dependency>
```

### MySQL Connector

Conexão da aplicação com o banco MySQL.

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

---

# 🏗 Arquitetura do Projeto

O sistema foi desenvolvido utilizando arquitetura em camadas.

## Controller

Responsável pelos endpoints REST.

* AlunoController
* ProfessorController
* ModalidadeController
* AulaController

## Service

Responsável pelas regras de negócio.

* AlunoService
* ProfessorService
* ModalidadeService
* AulaService

## DAO

Responsável pelo acesso ao banco de dados utilizando JDBC.

* AlunoDao
* ProfessorDao
* ModalidadeDao
* AulaDao

## Entity

Representação das entidades do sistema.

* Aluno
* Professor
* Modalidade
* Aula

---

# 📊 Entidades

## Aluno

Representa um aluno matriculado na academia.

### Atributos

* id
* nome
* email
* telefone
* sexo
* modalidades
* mensalidade

---

## Professor

Representa um professor da academia.

### Atributos

* id
* nome
* email
* salario
* tiposDeArteMarcial

---

## Modalidade

Representa uma modalidade oferecida pela academia.

### Atributos

* id
* tipo
* descricao
* valorMensalidade

---

## Aula

Representa as aulas disponíveis.

### Atributos

* id
* modalidadeId
* diaSemana
* horario

---

# 🔗 Relacionamentos

## Muitos para Muitos (N:M)

### Aluno ↔ Modalidade

Um aluno pode praticar várias modalidades.

Uma modalidade pode possuir vários alunos.

Tabela associativa:

```text
aluno_modalidades
```

---

### Professor ↔ Modalidade

Um professor pode ensinar várias modalidades.

Uma modalidade pode possuir vários professores.

Tabela associativa:

```text
professor_modalidades
```

---

## Um para Muitos (1:N)

### Modalidade → Aula

Uma modalidade pode possuir várias aulas.

Uma aula pertence a apenas uma modalidade.

---

# 📋 Regras de Negócio

## Restrição de Matrícula

Alunos do sexo masculino não podem ser matriculados na modalidade:

```text
BJJ_FEMININO
```

Caso isso ocorra, o sistema retorna erro.

---

## Cálculo Automático da Mensalidade

A mensalidade é calculada automaticamente através do padrão Strategy.

---

# 🎯 Design Patterns Utilizados

## Strategy

Responsável pelo cálculo da mensalidade dos alunos.

### PlanoUmaModalidade

* Aplicado quando o aluno possui apenas uma modalidade.

### PlanoVariasModalidades

* Aplicado quando o aluno possui duas modalidades.

* Desconto de 20%.

### PlanoFull

* Aplicado quando o aluno possui três ou mais modalidades.

---

## Factory Method

Responsável pela criação padronizada das modalidades.

### Factories

* BoxeFactory
* BjjFactory
* MuayThaiFactory
* BjjFemininoFactory

### Classe de Seleção

```text
ModalidadeFactorySelector
```

O Factory Method garante a criação padronizada das modalidades da academia.

---

# 🧹 Clean Code e SOLID

O projeto foi desenvolvido seguindo princípios de organização e manutenção de código:

* Separação por camadas
* Responsabilidade única
* Baixo acoplamento
* Alta coesão
* Nomenclaturas descritivas
* Regras de negócio centralizadas na camada Service

---

# 📦 Lombok

Utilizado para reduzir código repetitivo.

Anotações utilizadas:

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
```

---

# 📚 Documentação Swagger
## @schema

O projeto utiliza a anotação `@Schema` da biblioteca OpenAPI para documentar os campos das entidades exibidas no Swagger.

Principais funcionalidades:

- Descrição dos atributos
- Exemplos de preenchimento
- Restrição de valores permitidos para enums
- Identificação de campos somente leitura

Exemplo:

```java
@Schema(
    description = "Sexo do aluno",
    allowableValues = {"M", "F"},
    example = "F"
)
private Sexo sexo;

Após iniciar a aplicação:
```
## Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

## OpenAPI JSON

```text
http://localhost:8080/v3/api-docs
```

---

# 🌐 Endpoints

## Alunos

### Listar

```http
 http://localhost:8080/alunos
```


---

## Professores

### Listar

```http
 http://localhost:8080/professores
```



---

## Modalidades

### Listar

```http
 http://localhost:8080/modalidades
```


---

## Aulas

### Listar

```http
 http://localhost:8080/aulas
```


---

# 🗄 Banco de Dados


## Script SQL

Cole abaixo o script SQL utilizado para criação do banco e tabelas.

```sql
CREATE DATABASE IF NOT EXISTS btt;

USE btt;

CREATE TABLE alunos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(20),
    sexo VARCHAR(20),
    mensalidade DOUBLE
);

CREATE TABLE aluno_modalidades (
    aluno_id BIGINT,
    modalidade VARCHAR(50),

    FOREIGN KEY (aluno_id)
    REFERENCES alunos(id)
    ON DELETE CASCADE
);

CREATE TABLE professores (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    salario DOUBLE
);

CREATE TABLE professor_modalidades (
    professor_id BIGINT,
    modalidade VARCHAR(50),

    FOREIGN KEY (professor_id)
    REFERENCES professores(id)
    ON DELETE CASCADE
);

CREATE TABLE modalidades (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    valor_mensalidade DOUBLE
);

CREATE TABLE aulas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    modalidade_id BIGINT NOT NULL,
    dia_semana VARCHAR(50),
    horario VARCHAR(50),

    FOREIGN KEY (modalidade_id)
    REFERENCES modalidades(id)
    ON DELETE CASCADE
);
```
---

```sql

SELECT * FROM alunos;
SELECT * FROM professores;
SELECT * FROM modalidades;
SELECT * FROM aulas;
SELECT * FROM aluno_modalidades;
SELECT * FROM professor_modalidades;

SELECT a.nome, am.modalidade
FROM alunos a
JOIN aluno_modalidades am
ON a.id = am.aluno_id;

SELECT p.nome, pm.modalidade
FROM professores p
JOIN professor_modalidades pm
ON p.id = pm.professor_id;

SHOW TABLES;
```
---

```sql
USE btt;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE aluno_modalidades;
TRUNCATE TABLE professor_modalidades;
TRUNCATE TABLE aulas;
TRUNCATE TABLE alunos;
TRUNCATE TABLE professores;
TRUNCATE TABLE modalidades;

SET FOREIGN_KEY_CHECKS = 1;

```
---
