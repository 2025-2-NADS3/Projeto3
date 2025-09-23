# 🚀 CRUD de Login - Node.js e MySQL

## 🔗 Explicação
---
Este projeto é um CRUD (Create, Read, Update, Delete) completo para gerenciamento de usuários. Foi desenvolvido em JavaScript, utilizando um servidor **Node.js** para o backend e um banco de dados **MySQL** para armazenamento de dados.

## ⚙️ Como Rodar o Projeto
---
### Pré-requisitos

Para rodar o projeto, é necessário ter o **Node.js** e o **MySQL Server + Workbench** baixados e instalados em seu computador.

### 🗄️ Configuração do Banco de Dados (MySQL)

Abra o MySQL Workbench e execute o script SQL abaixo para criar o banco de dados e a tabela do projeto.

```sql
CREATE DATABASE IF NOT EXISTS simple_crud_login;

USE simple_crud_login;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```

### 🖥️ Configuração do Servidor (Node.js)

**1. Baixar as dependências:**

Para baixar as dependências do projeto, navegue até a pasta `backend` e rode o seguinte comando no seu terminal:
```bash
cd backend
npm install
```

**Importante:** Antes de iniciar o servidor, abra o arquivo `backend/server.js` e configure suas credenciais de acesso ao banco de dados:
```javascript
const db = await mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: 'sua_senha_aqui', // <-- COLOQUE SUA SENHA DO MYSQL AQUI
    database: 'simple_crud_login'
});
```

**2. Executar o projeto:**

Depois de instalar as dependências e configurar a senha, para executar o servidor, rode o seguinte comando (ainda na pasta `backend`):
```bash
node server.js
```
O terminal deverá exibir a mensagem: `🚀 Servidor rodando em http://localhost:3001`


## 🚀 Rodando o Frontend
---
Com o servidor backend rodando (não feche o terminal!), abra o arquivo `frontend/index.html` em seu navegador.

> **Dica:** A forma mais fácil de fazer isso é usando a extensão **"Live Server"** no VS Code. Basta clicar com o botão direito no arquivo `index.html` e selecionar "Open with Live Server".

Agora a aplicação está 100% funcional e pronta para uso.
