# 📋 CHANGELOG

Todas as alterações relevantes deste projeto serão documentadas aqui.  
Este arquivo segue o formato de versionamento semântico ([semver](https://semver.org/lang/pt-BR/)).

---

## [v1.0.0] - 2025-04-08
### 🚀 Primeira versão estável
- Cadastro de livros com acervo (físico e digital)
- Cadastro e gerenciamento de usuários
- Persistência em arquivos JSON com Jackson
- Interface via terminal com menu interativo
- Testes automatizados com JUnit 5
- Relatórios de cobertura com JaCoCo
- Documentação JavaDoc gerada via Maven
- Esteira CI com GitHub Actions
- Estrutura de desenvolvimento com Git Flow
- Projeto acadêmico da PUCPR (Programação Orientada a Objetos)

---

## [1.1.1-SNAPSHOT] - Em desenvolvimento
### Adicionado
- Branch `feature/fila-e-pilha` criada para implementação da atividade formativa.
- Implementação de **fila (Queue)** para lista de espera de livros.
- Implementação de **pilha (Stack)** para histórico de navegação de usuários.
- Menu da interface expandido para suportar visualização de histórico e espera.
- Testes automatizados para filas e pilhas.
- Documentação atualizada (`README.md`, `pom.xml`, `CONTRIBUTING.md`, `CHANGELOG.md`).
- SCM e `developers` configurados no `pom.xml`.

### Alterado

- Atualização do `pom.xml` para `1.1.0-SNAPSHOT`.

## [v1.2.0] - 2025-04-09 (pré-release)

### ✨ Adicionado
- Implementado grafo de recomendações entre livros (`HashMap<Book, Set<Book>>`);
- Persistência das recomendações em `books.json`;
- Menu expandido com:
    - `19` - Exibir recomendações de um livro;
    - `20` - Adicionar recomendação entre dois livros;
- Testes automatizados para o grafo com **JUnit 5**:
    - `RecommendationGraphTest`;
    - `RecommendationFeatureTest`;
- Modularização da suíte de testes:
    - `WaitlistFeatureTest` (fila de espera);
    - `UserHistoryFeatureTest` (histórico de navegação);
- Suite de testes geral com `SuiteTest.java`;
- Arquivos de teste JSON dedicados para cada módulo:
    - `test-books-recommendation.json`;
    - `test-users-recommendation.json`;
    - `test-books-history.json`, etc.

### 🔧 Alterado
- `LibraryFileManager` atualizado para salvar/restaurar o grafo de recomendações;
- `Main.java` atualizado para refletir o carregamento e visualização de recomendações;
- Testes existentes atualizados para refletir a nova estrutura de arquivos de teste.

---

📌 Esta versão implementa a **funcionalidade de grafo de recomendações** como parte da atividade **somativa** de **Programação Orientada a Objetos – PUCPR**.

⚠️ **Pré-release:** Funcionalidade nova, ainda em testes finais de integração.
=======
- Versão `pom.xml` para `1.1.1-SNAPSHOT`.
- README com seção da atividade formativa da PUCPR.

---

## [1.2.0] - 2025-04-09

### ✨ Adicionado

- Nova branch `feature/grafo-recomendacao` com entrega final da atividade somativa.
- Implementação de **grafo de recomendações entre livros**.
- Extensão do sistema de navegação com histórico por usuário (padrão Stack).
- Módulo de **testes automatizados** por recurso:
    - `RecommendationFeatureTest.java`
    - `UserHistoryFeatureTest.java`
    - `WaitlistFeatureTest.java`
- Criação de **SuiteTest.java** para execução agrupada dos testes.
- Criação de arquivos JSON separados para cenários de teste:
    - `test-books-recommendation.json`, `test-users-history.json`, etc.
- Atualização do `Main.java` para suportar visualização e adição de recomendações.
- Documentação complementar de testes (`README_TESTS.md`).

### 🛠️ Alterado

- `LibraryFileManager` agora persiste o grafo de recomendações no `books.json`.
- Ajustes no menu interativo para integrar com o grafo e histórico.
- Modularização e refatoração de testes (`MainMenuTest` dividido em arquivos menores).
- Atualização de testes existentes com maior robustez.

### ✅ Corrigido

- Correções na persistência do grafo de recomendações que não estava sendo salva entre execuções.

