# Order Service - Spring Boot & Apache Kafka

Este projeto é uma aplicação desenvolvida em **Spring Boot** que implementa o fluxo de gestão de pedidos (Orders) integrado com um cluster de **Apache Kafka** para mensageria assíncrona baseada em eventos no formato JSON.

---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (Web, Data JPA, Kafka)
* **Apache Kafka** (Mensageria e Event Streaming)
* **Docker & Docker Compose** (Containerização do Kafka)
* **Banco de Dados Relacional** (PostgreSQL / H2)
* **Lombok** (Redução de boilerplate)
* **Jackson** (Serialização e desserialização de JSON)

---

## 🛠️ Arquitetura do Fluxo (Kafka)

1. **Producer (`OrderProducer`)**: Quando um novo pedido é inserido com sucesso na base de dados, a aplicação publica um evento estruturado (`OrderEventDTO`) num tópico do Kafka (`order-topic-v2`).
2. **Consumer (`OrderConsumer`)**: O serviço escuta o tópico em tempo real, consome o evento JSON, converte-o de volta para o DTO e processa a lógica de negócio correspondente de forma assíncrona.

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
