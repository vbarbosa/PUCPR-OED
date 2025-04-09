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
- Esteira de desenvolvimento com Git e GitHub Actions.

---

# 🧩 Fluxo de Desenvolvimento - Projeto PUCPR-OED

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
│   │   │   ├── controller/      # Biblioteca (Library)
│   │   │   ├── manager/         # Persistência JSON
│   │   │   └── model/           # Book, User, InventoryItem
│   │   └── resources/           # books.json, users.json
│   └── test/
│       ├── java/com/vinot/somativa1/ # Testes automatizados
│       └── resources/           # JSONs de teste
├── .github/
│   └── workflows/build.yml      # CI automático com Maven
├── .gitignore
├── CONTRIBUTING.md
├── LICENSE
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
java -cp target/Somativa1-1.0-SNAPSHOT.jar com.vinot.somativa1.application.Main
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
target/Somativa1-1.0-SNAPSHOT-javadoc.jar
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

### 🚧 Desenvolvimento de funcionalidades
```bash
git checkout develop
git checkout -b feature/nome-da-feature
# alterações...
git add .
git commit -m "feat: descrição da feature"
git push -u origin feature/nome-da-feature
```
Depois, abrir Pull Request para `develop`.

### 🔄 Atualizar main (produção)
```bash
git checkout main
git merge develop
git push
```

### 🔥 Corrigir bug crítico
```bash
git checkout main
git checkout -b hotfix/nome-do-bug
# corrigir

git commit -am "fix: bug crítico"
git checkout main
git merge hotfix/nome-do-bug
git push
```

---

## 📦 Criar uma Release e Tag

### Passos para criar release com changelog
1. Atualize `pom.xml` com nova versão, ex: `1.0.0`
2. Faça commit: `git commit -am "chore: release 1.0.0"`
3. Gere a tag:
```bash
git tag -a v1.0.0 -m "Versão 1.0.0 estável"
git push origin v1.0.0
```
4. Vá no GitHub > Releases > Draft new release > selecione a tag `v1.0.0`
5. Escreva um changelog (resumo das mudanças)
6. Anexe arquivos (`.jar`, `.javadoc.jar`, etc) se desejar
7. Publicar release

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

- Toda `push` em `main` ou `develop` dispara build e testes
- Toda PR para `develop` também roda CI

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
```markdown
# Como Contribuir
1. Fork
2. Branch `feature/*`
3. Commits pequenos e claros
4. Pull Request para `develop`
5. Aguardar revisão
```

### `LICENSE`
Licença MIT (padrão de projetos open-source acadêmicos)
```txt
MIT License

Copyright (c) 2025 Vinícius Barbosa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
... (continua)
```

---

## ✅ Checklist de Commits
- [ ] Compila e passa os testes
- [ ] Testes automatizados se necessário
- [ ] Sem arquivos de build no commit (`target/`, `.class`, etc)
- [ ] Inclui documentação (se aplicável)

---

## 📞 Contato
Vinícius Barbosa
> Projeto acadêmico da PUCPR - 2025

