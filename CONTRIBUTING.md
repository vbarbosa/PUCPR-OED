# 🧠 Como Contribuir

Este projeto segue o fluxo de trabalho baseado em Git Flow, com uso das branches `main`, `develop`, `feature/*`, `hotfix/*` e `test/*`.

---

## 📌 Etapas para contribuir

1. Faça **fork** do repositório:
   ```
   https://github.com/vbarbosa/PUCPR-OED
   ```

2. Clone seu fork e acesse o diretório:
   ```bash
   git clone https://github.com/seu-usuario/PUCPR-OED.git
   cd PUCPR-OED
   ```

3. Certifique-se de estar atualizado com a branch `develop`:
   ```bash
   git checkout develop
   git pull origin develop
   ```

4. Crie uma nova branch para sua contribuição:
   ```bash
   git checkout -b feature/nome-da-sua-funcionalidade
   ```

5. Faça suas alterações no código-fonte, documentação e testes.

6. Faça commits pequenos, claros e semânticos:
   ```bash
   git add .
   git commit -m "feat: adiciona fila de espera para livros"
   ```

7. Envie sua branch para seu fork:
   ```bash
   git push -u origin feature/nome-da-sua-funcionalidade
   ```

8. Acesse o GitHub e abra um **Pull Request** para a branch `develop`.

---

## ✅ Checklist antes de abrir Pull Request

- [ ] ✅ O código compila sem erros.
- [ ] ✅ Todos os testes automatizados (JUnit) passam.
- [ ] ✅ Foram incluídos testes novos se necessário.
- [ ] ✅ Mensagens de commit seguem convenções (`feat:`, `fix:`, `refactor:`, etc).
- [ ] ✅ Nenhum arquivo de build foi incluído no commit (`target/`, `.class`, `.log`...).
- [ ] ✅ A documentação foi atualizada (`README.md`, JavaDocs, etc).
- [ ] ✅ A branch está nomeada conforme o padrão (`feature/...`, `hotfix/...`).
- [ ] ✅ O Pull Request está direcionado para a branch `develop`.
- [ ] ✅ A descrição do PR explica claramente a motivação e o que foi alterado.

---

## 📘 Convenções de nomeação de branches

- `feature/nome-da-funcionalidade`: novas features
- `hotfix/descricao-do-bug`: correções pontuais na produção
- `test/teste-especifico`: testes específicos ou experimentais

---

## 💡 Dicas

- Sempre escreva testes para funcionalidades novas.
- Atualize o JavaDoc e o README se necessário.
- Use `mvn clean verify` para validar antes do push.

---

Vinicius (vinot) — PUCPR 2025
