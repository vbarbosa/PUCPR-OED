# Somativa1 - Sistema de Gerenciamento de Biblioteca Virtual

Projeto Java completo com suporte a:

- Cadastro de livros com acervo (físico e digital);
- Cadastro e busca de usuários;
- Persistência em arquivos JSON;
- Interface via terminal com menu interativo;
- Testes automatizados com JUnit 5;
- Geração de documentação JavaDoc;
- Relatório de cobertura de testes com JaCoCo;
- Estrutura Maven profissional;
- ✅ **Implementação de fila e pilha como parte da atividade formativa da PUCPR.**

---

## 📌 Atividade Formativa - PUCPR

Nesta etapa do projeto, foram integradas novas funcionalidades com foco no uso de **estruturas de dados**:

- 📚 **Fila (`Queue`)**: gerenciamento da lista de espera de livros emprestados.
- 🧠 **Pilha (`Stack`)**: histórico de navegação dos livros visualizados por cada usuário.
- Menu interativo com opções para adicionar à fila, consultar e visualizar histórico.
- Mantida a estrutura com `LinkedList<Book>` como base do acervo.

---

## 🧩 Fluxo de Desenvolvimento - Projeto PUCPR-OED

Este documento descreve o processo completo de versionamento, colaboração e esteira de CI/CD com Git e GitHub para o projeto Java **Somativa1 - Sistema de Gerenciamento de Biblioteca Virtual**.

---

## 📚 Contexto Acadêmico
Projeto desenvolvido como atividade somativa na disciplina de **Programação Orientada a Objetos** da **PUCPR**, com foco em boas práticas de desenvolvimento, testes e documentação.

---

## 🧱 Estrutura de Diretórios
```
Somativa1/
├── pom.xml                       # Configuração Maven
├── target/                      # Build, relatórios, JavaDoc
├── src/
│   ├── main/
│   │   ├── java/com/vinot/somativa1/
│   │   │   ├── application/     # Classe Main
│   │   │   ├── controller/      # Biblioteca, fila e histórico
│   │   │   ├── manager/         # Persistência JSON
│   │   │   └── model/           # Book, User, InventoryItem
│   │   └── resources/           # books.json, users.json
│   └── test/
│       ├── java/com/vinot/somativa1/ # Testes automatizados
│       └── resources/           # JSONs de teste
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 23 (ex: Temurin 23 via SDKMAN ou Scoop)
- Maven 3.9+

### Compilar o Projeto
```bash
mvn clean compile
```

### Executar a Aplicação
```bash
java -cp target/Somativa1-1.1.1-SNAPSHOT.jar com.vinot.somativa1.application.Main
```

---

## ✅ Executar os Testes
```bash
mvn test
```

- Testes com JUnit 5
- Pastas: `src/test/java` e `src/test/resources`

---

## 🧪 Cobertura de Testes (JaCoCo)
```bash
mvn verify
```

- Acessar relatório:
  ```
  target/site/jacoco/index.html
  ```

---

## 📗 Gerar JavaDoc

### Gerar Documentação HTML
```bash
mvn javadoc:javadoc
```
Abrir em:
```
target/site/apidocs/index.html
```

### Gerar JAR da documentação
```bash
mvn javadoc:jar
```
Saída:
```
target/Somativa1-1.1.1-SNAPSHOT-javadoc.jar
```

---

## ⚙️ Plugins Maven Utilizados
- `maven-compiler-plugin`
- `maven-surefire-plugin`
- `maven-javadoc-plugin`
- `jacoco-maven-plugin`

---

## 💼 Estrutura de Branches Git

- `main`: versão estável
- `develop`: integração
- `feature/*`: novas funcionalidades
- `hotfix/*`: correções rápidas
- `test/*`: testes específicos

---

## 🔁 Fluxo de Trabalho Git

```bash
git clone https://github.com/vbarbosa/PUCPR-OED.git
cd PUCPR-OED

git checkout -b develop     # cria develop (se necessário)
git checkout -b feature/nova-feature
# fazer mudanças

git add .
git commit -m "feat: nova feature"
git push -u origin feature/nova-feature
```

Após isso:
1. Acesse o GitHub
2. Crie um Pull Request para `develop`

### Merge para Produção
```bash
git checkout main
git merge develop
git push
```

### Corrigir bug urgente
```bash
git checkout main
git checkout -b hotfix/bug
# corrigir

git commit -am "fix: bug"
git checkout main
git merge hotfix/bug
git push
```

---

## 🤖 GitHub Actions - CI/CD

`.github/workflows/build.yml`
```yaml
name: CI

on:
  push:
    branches: [ develop, main ]
  pull_request:
    branches: [ develop ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - uses: actions/setup-java@v3
      with:
        distribution: 'temurin'
        java-version: '23'

    - name: Build com Maven
      run: mvn clean verify
```

---

## 🗂️ Arquivos Adicionais

### `.gitignore`
```gitignore
target/
.idea/
*.class
*.log
*.iml
*.jar
*.exec
.DS_Store
```

### `CONTRIBUTING.md`
Ver instruções completas no arquivo [`CONTRIBUTING.md`](./CONTRIBUTING.md)

---

## ✅ Checklist de Commits
- [ ] Código compila e passa os testes
- [ ] Testes automatizados cobrindo a funcionalidade
- [ ] Sem arquivos de build no commit (`target/`, `.class`, etc)
- [ ] Inclui documentação (README, JavaDoc, etc)

---

## 📞 Contato
Vinícius Barbosa
> Projeto acadêmico da PUCPR - 2025
