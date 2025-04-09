# ✅ Testes Automatizados — Projeto Somativa1

Este projeto utiliza **JUnit 5** para validar as funcionalidades principais da aplicação de biblioteca virtual.

## 📦 Estrutura dos Testes

| Arquivo Java                   | Objetivo                                                       |
|-------------------------------|----------------------------------------------------------------|
| `MainMenuTest.java`           | Testa funcionalidades principais do menu interativo (`Main`)  |
| `BookQueueTest.java`          | Testa a fila de espera por livro (`opções 16 e 17`)            |
| `UserHistoryTest.java`        | Testa o histórico de navegação por usuário (`opção 18`)        |
| `RecommendationGraphTest.java` | Testa o grafo de recomendações (`opções 19 e 20`)             |

---

## ▶️ Executando todos os testes

Use sua IDE (IntelliJ, Eclipse) ou o Maven:

```bash
./mvnw test
