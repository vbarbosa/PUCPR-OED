# 📋 CHANGELOG

Todas as alterações relevantes deste projeto serão documentadas aqui.
Este arquivo segue o formato de versionamento semântico (https://semver.org/lang/pt-BR/).

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

## [1.1.0-SNAPSHOT] - Em desenvolvimento

### Adicionado
- Documentação atualizada com CI/CD, licenciamento, changelog e estrutura Git.
- Primeira release estável: `v1.0.0`.

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
