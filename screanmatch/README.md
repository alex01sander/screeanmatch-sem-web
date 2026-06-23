# 🎬 ScreenMatch — API REST de Catálogo de Séries

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/REST_API-005571?style=for-the-badge&logo=swagger&logoColor=white"/>
</p>

> **ScreenMatch** é uma API REST construída com Spring Boot que gerencia um catálogo completo de séries e episódios, integrada ao banco de dados PostgreSQL e preparada para consumo por frontends web modernos.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Endpoints da API](#-endpoints-da-api)
- [Modelos de Dados](#-modelos-de-dados)
- [Como Executar](#-como-executar)
- [Variáveis de Ambiente](#-variáveis-de-ambiente)
- [Integração com IA (ChatGPT)](#-integração-com-ia-chatgpt)

---

## 📖 Sobre o Projeto

O **ScreenMatch** é um projeto fullstack desenvolvido durante o curso da **Alura**, com foco na criação de uma API REST robusta em Java com Spring Boot. A aplicação permite:

- 🔍 Buscar séries por gênero, avaliação e lançamentos recentes
- ⭐ Listar o Top 5 séries melhor avaliadas
- 📺 Consultar temporadas e episódios de uma série
- 🌐 Servir dados para um frontend web via CORS configurado
- 🤖 (Opcional) Integração com a API do ChatGPT para tradução de sinopses

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework web e IoC |
| Spring Data JPA | — | Persistência e acesso a dados |
| Spring Web | — | Criação dos endpoints REST |
| PostgreSQL | — | Banco de dados relacional |
| Jackson Databind | 2.21.3 | Serialização/desserialização JSON |
| OpenAI GPT-3 Java | 0.14.0 | Integração com ChatGPT (opcional) |
| Spring DevTools | — | Hot reload em desenvolvimento |
| Maven | — | Gerenciador de dependências |

---

## 🏗 Arquitetura do Projeto

O projeto segue o padrão **MVC em camadas**, separando responsabilidades de forma clara:

```
src/main/java/br/com/alura/screanmatch/
│
├── config/
│   └── CorsConfiguration.java       # Configuração de CORS para o frontend
│
├── controller/
│   └── SerieController.java          # Endpoints REST expostos
│
├── dto/
│   ├── SerieDTO.java                 # Transferência de dados de série
│   └── EpisodioDTO.java              # Transferência de dados de episódio
│
├── model/
│   ├── Serie.java                    # Entidade JPA da série
│   ├── Episodio.java                 # Entidade JPA do episódio
│   ├── Categoria.java                # Enum de gêneros/categorias
│   ├── DadosSerie.java               # Record para dados da API OMDB
│   ├── DadosTemporada.java           # Record para dados de temporada
│   └── DadosEpisodio.java            # Record para dados de episódio
│
├── repository/
│   └── SerieRepository.java          # Queries JPQL e Spring Data
│
├── service/
│   ├── SerieService.java             # Regras de negócio
│   ├── ConsultaChatGPT.java          # Integração com OpenAI
│   ├── ConsumoApi.java               # Consumo de APIs externas (HTTP)
│   ├── ConverteDados.java            # Conversão JSON → Objetos Java
│   └── IConverteDados.java           # Interface de conversão
│
└── ScreanmatchApplication.java       # Classe principal (com API OMDB)
    ScreanmatchApplicationSemWeb.java # Versão de console (sem web)
```

---

## 🔌 Endpoints da API

Base URL: `http://localhost:8080`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/series` | Lista todas as séries |
| `GET` | `/series/top5` | Top 5 séries por avaliação |
| `GET` | `/series/lancamentos` | Últimas séries com episódios recentes |
| `GET` | `/series/{id}` | Busca uma série pelo ID |
| `GET` | `/series/{id}/temporadas/todas` | Todos os episódios de uma série |
| `GET` | `/series/{id}/temporadas/{numero}` | Episódios de uma temporada específica |
| `GET` | `/series/categoria/{nomeGenero}` | Filtra séries por gênero (ex: `Drama`) |

### Exemplo de Resposta — `GET /series/top5`

```json
[
  {
    "id": 1,
    "titulo": "Breaking Bad",
    "totalTemporadas": 5,
    "avaliacao": 9.5,
    "genero": "DRAMA",
    "atores": "Bryan Cranston, Aaron Paul",
    "poster": "https://...",
    "sinopse": "..."
  }
]
```

---

## 📊 Modelos de Dados

### Serie (Entidade JPA)
| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | Identificador único (auto gerado) |
| `titulo` | `String` | Título da série |
| `totalTemporadas` | `Integer` | Número de temporadas |
| `avaliacao` | `Double` | Nota média da série |
| `genero` | `Categoria` | Enum de gênero |
| `atores` | `String` | Elenco principal |
| `poster` | `String` | URL da imagem do poster |
| `sinopse` | `String` | Descrição da série |
| `episodios` | `List<Episodio>` | Relacionamento One-to-Many |

### Categorias disponíveis
| Valor no banco | Nome em português |
|---|---|
| `ACAO` | Ação |
| `ROMANCE` | Romance |
| `COMEDIA` | Comédia |
| `DRAMA` | Drama |
| `CRIME` | Crime |

---

## 🚀 Como Executar

### Pré-requisitos

- ✅ Java 17 ou superior instalado
- ✅ Maven instalado
- ✅ PostgreSQL instalado e rodando
- ✅ Um banco de dados PostgreSQL criado (ex: `screenmatch`)

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/screenmatch.git
cd screenmatch/screanmatch
```

**2. Configure as variáveis de ambiente** (veja a seção abaixo)

**3. Execute a aplicação**
```bash
./mvnw spring-boot:run
```
Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

**4. Acesse a API**
```
http://localhost:8080/series
```

---

## ⚙ Variáveis de Ambiente

Configure as seguintes variáveis de ambiente antes de executar:

| Variável | Descrição | Exemplo |
|---|---|---|
| `DB_HOST` | Host do banco de dados | `localhost:5432` |
| `DB_NAME` | Nome do banco de dados | `screenmatch` |
| `DB_USER` | Usuário do PostgreSQL | `postgres` |
| `DB_PASSWORD` | Senha do PostgreSQL | `sua_senha` |

### Como configurar no Windows (PowerShell):
```powershell
$env:DB_HOST="localhost:5432"
$env:DB_NAME="screenmatch"
$env:DB_USER="postgres"
$env:DB_PASSWORD="sua_senha"
```

### Como configurar no Linux/Mac:
```bash
export DB_HOST=localhost:5432
export DB_NAME=screenmatch
export DB_USER=postgres
export DB_PASSWORD=sua_senha
```

> ⚠️ **Nunca commite credenciais reais no repositório!**

---

## 🤖 Integração com IA (ChatGPT)

O projeto possui suporte à integração com a API da **OpenAI (GPT-3.5)** para tradução automática de sinopses. Para habilitá-la:

1. Obtenha sua chave de API em [platform.openai.com](https://platform.openai.com)
2. Descomente o código em `ConsultaChatGPT.java`:
   ```java
   public static String obterTraducao(String texto) {
       OpenAiService service = new OpenAiService("SUA_CHAVE_AQUI");
       // ...
   }
   ```
3. Descomente a linha em `Serie.java`:
   ```java
   this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.sinopse()).trim();
   ```

---

## 🌐 CORS

A API está configurada para aceitar requisições dos seguintes origens (frontend local):

- `http://127.0.0.1:5500`
- `http://127.0.0.1:5501`
- `http://localhost:5500`
- `http://localhost:5501`

Para adicionar outras origens, edite o arquivo [CorsConfiguration.java](src/main/java/br/com/alura/screanmatch/config/CorsConfiguration.java).

---

## 👨‍💻 Autor

Desenvolvido durante o curso **Java e Spring Boot** da **[Alura](https://www.alura.com.br)**.

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
