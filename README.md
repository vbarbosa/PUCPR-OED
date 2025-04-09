# Somativa1 - Sistema de Gerenciamento de Biblioteca Virtual

Projeto Java completo com suporte a:
- Cadastro de livros com acervo (físico e digital);
- Cadastro e busca de usuários;
- Persistência em arquivos JSON;
- Interface via terminal com menu interativo;
- Testes automatizados com JUnit 5;
- Geração de documentação JavaDoc;
- Relatório de cobertura de testes com JaCoCo;
- Estrutura Maven profissional.

---

## 🧱 Estrutura de Diretórios

```
Somativa1/
├── pom.xml                       # Configuração Maven
├── target/                      # Diretório de build gerado
│   └── site/                    # Javadoc e relatórios do JaCoCo
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/vinot/somativa1/
│   │   │       ├── application/   # Classe Main
│   │   │       ├── controller/    # Biblioteca (Library)
│   │   │       ├── manager/       # Leitura/Escrita JSON
│   │   │       └── model/         # Book, User, InventoryItem
│   │   └── resources/             # Arquivos JSON persistentes
│   │       ├── books.json
│   │       └── users.json
│   └── test/
│       ├── java/
│       │   └── com/vinot/somativa1/
│       │       └── ... (classes de teste unitário)
│       └── resources/             # books-test.json, users-test.json
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 23 instalado (ex: Temurin 23)
- Maven 3.9+

### Compilar o projeto
```bash
mvn clean compile
```

### Executar a aplicação (via terminal):
```bash
java -cp target/Somativa1-1.0-SNAPSHOT.jar com.vinot.somativa1.application.Main
```

---

## ✅ Executar os Testes

```bash
mvn test
```

- Utiliza JUnit 5
- Todos os testes estão em `src/test/java`
- Gera arquivo de cobertura `target/jacoco.exec`

---

## 🧪 Ver Cobertura de Testes com JaCoCo

```bash
mvn verify
```

Relatório HTML completo:
```
target/site/jacoco/index.html
```

Abra no navegador e veja a porcentagem de cobertura por classe, método e linha.

---

## 📚 Gerar JavaDoc

```bash
mvn javadoc:javadoc
```

Saída:
```
target/site/apidocs/index.html
```

Para gerar o `.jar` da documentação:
```bash
mvn javadoc:jar
```

Saída:
```
target/Somativa1-1.0-SNAPSHOT-javadoc.jar
```

---

## ⚙️ Plugins Maven Utilizados

```xml
<plugins>
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
      <release>23</release>
    </configuration>
  </plugin>

  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
  </plugin>

  <plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.12</version>
    <executions>
      <execution>
        <id>prepare-agent</id>
        <goals><goal>prepare-agent</goal></goals>
      </execution>
      <execution>
        <id>report</id>
        <phase>verify</phase>
        <goals><goal>report</goal></goals>
      </execution>
    </executions>
  </plugin>

  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>3.6.3</version>
    <executions>
      <execution>
        <id>attach-javadocs</id>
        <phase>package</phase>
        <goals><goal>jar</goal></goals>
      </execution>
    </executions>
    <configuration>
      <source>23</source>
      <show>private</show>
      <doclint>none</doclint>
      <subpackages>com.vinot.somativa1</subpackages>
      <additionalJOption>-private</additionalJOption>
    </configuration>
  </plugin>
</plugins>
```

---

## ✨ Recursos Utilizados

- **Jackson**: manipulação de JSON
- **JUnit 5**: testes automatizados
- **JaCoCo**: cobertura de testes
- **JavaDoc**: documentação automatizada
- **Maven**: gerenciamento de dependências, build e plugins

---

## 📞 Suporte
Vinícius Barbosa

> Projeto acadêmico - 

