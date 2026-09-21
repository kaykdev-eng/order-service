# Order Service - Spring Boot & Apache Kafka

Este projeto é uma aplicação desenvolvida em **Spring Boot** que implementa o fluxo de gestão de pedidos (Orders) integrado com um cluster de **Apache Kafka** para mensageria assíncrona baseada em eventos no formato JSON.

---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (Web, Data JPA, Kafka)
* **Apache Kafka** (Mensageria e Event Streaming)
* **Docker & Docker Compose** (Containerização do Kafka)
* **PostgreSQL / H2** (Banco de Dados Relacional)
* **Lombok** (Redução de código boilerplate)
* **Jackson** (Serialização e desserialização de JSON)
* **Springdoc OpenAPI / Swagger** (Documentação interativa da API)

---

## 🛠️ Arquitetura do Fluxo (Kafka)

1. **Producer (`OrderProducer`)**: Quando um novo pedido é inserido com sucesso na base de dados, a aplicação publica um evento estruturado (`OrderEventDTO`) num tópico do Kafka (`order-topic-v2`).
2. **Consumer (`OrderConsumer`)**: O serviço escuta o tópico em tempo real, consome o evento JSON, converte-o de volta para o DTO e processa a lógica de negócio correspondente de forma assíncrona.

---

## 📖 Documentação da API (Swagger UI)

Este projeto utiliza o **Springdoc OpenAPI** para gerar automaticamente a documentação interativa da API. Não é necessário manter documentos estáticos ou utilizar ferramentas externas; a documentação é construída e atualizada em tempo real através da leitura das anotações do próprio código (como `@RestController`, `@PostMapping`, etc.).

### Como aceder:
Com a aplicação em execução, abre o teu navegador de internet e acede à interface gráfica do Swagger:
👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

*(Se desejas ver apenas o contrato em formato JSON bruto, podes aceder a `http://localhost:8080/v3/api-docs`)*

### Como utilizar a interface:
1. **Explorar:** A página inicial lista todos os endpoints da aplicação (por exemplo, `POST /orders`).
2. **Interagir:** Clica no endpoint que desejas testar para expandir os detalhes e carrega no botão **"Try it out"** (Testar).
3. **Preencher Dados:** Na área do corpo da requisição (Request body), insere o JSON com os dados do teu pedido.
4. **Executar:** Carrega no botão **"Execute"**. O Swagger fará a chamada real à tua API e mostrará o resultado exato na secção "Responses" (incluindo o HTTP Status Code, como `201 Created`, e o retorno do servidor).

---

## ⚙️ Pré-requisitos

Certifica-te de que tens instalado no teu ambiente:
* **Java Development Kit (JDK 17 ou superior)**
* **Docker e Docker Compose** (para subir o Kafka)
* **Maven** (ou utilizar o wrapper incluído `mvnw`)

---

## 🐳 Subir o Kafka via Docker

Se ainda não tens o Kafka a correr no teu ambiente local, podes subi-lo rapidamente através do Docker com o seguinte comando na raiz do projeto:

```bash
docker run -d --name my-kafka -p 9092:9092 apache/kafka:latest
