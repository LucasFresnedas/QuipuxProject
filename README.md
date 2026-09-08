# Quipux API 

API RESTful desenvolvida em **Java** com **Spring Boot**, autenticação via **JWT (JSON Web Token)** e cadastro de pessoas. O projeto conta com uma interface web simples para login/cadastro e documentação interativa via **Swagger UI**.

---

## Tecnologias Utilizadas

* **Java 17** (ou versão superior)
* **Spring Boot 3**
* **Spring Security** (Autenticação e Autorização)
* **Spring Data MongoDB** (Persistência NoSQL)
* **JWT (io.jsonwebtoken)** (Geração e validação de tokens)
* **OpenAPI 3 / Swagger UI** (Documentação interativa da API)
* **Lombok** (Produtividade no código)
* **HTML5 + Tailwind CSS** (Interface web integrada no repositório)

---

## Funcionalidades

- [x] **Cadastro e Autenticação de Usuários:** Registro e emissão de tokens JWT.
- [x] **Filtro de Segurança JWT:** Proteção de endpoints sensíveis através do header `Authorization: Bearer <token>`.
- [x] **Gestão de Pessoas:** CRUD de pessoas vinculado à coleção específica do banco NoSQL.
- [x] **Interface Web:** Páginas de Login (`index.html`) e Cadastro (`cadastro.html`) com suporte a Dark Mode.
- [x] **Documentação Interativa:** Swagger UI configurado para autenticação rápida com o token gerado.

---

## Como Executar o Projeto

### Pré-requisitos

* Java 17+ instalado
* Maven instalado

### Passos para execução

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/LucasFresnedas/QuipuxProject.git
   cd SEU-REPOSITORIO

2. **Executará normalmente se você possuir os pré requisitos**

OBS:

A aplicação conta com um banco NoSQL online, que todo IP pode acessar. 
Mas todas as variaveis de acesso ao banco são temporárias, inclusive o acesso de qualquer IP, está liberada apenas pelo teste para os responsáveis executarem o projeto. 

Caso ocorra das variaveis do banco expirar por favor entre em contato comigo para solicita-las novamente. 

Fluxo de execução:
1 - Rodar o projeto 
2 - Abrir a interface web
3 - Se cadastrar 
4 - Logar com o seu cadastro <- Nessa etapa é gerado o token no sucesso
5 - Copiar o token
6 - Clicar no link do swagger
7 - Colar o token no campo que surge ao clicar no cadeado
8 - Você pode testar todos os endpoints.

É importante lembrar que se deve utilizar a interface web pela porta 8080(mesma da api)
pois se usada pela 127.0.0.1 você terá problemas de 403. 

A validação do cpf no cadastro da pessoa está feita seguindo a lógica de um cpf real.

recomendo gerar um cpf sem pontuação pelo link: https://www.4devs.com.br/gerador_de_cpf

