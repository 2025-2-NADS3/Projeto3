// backend/server.js

import express from 'express';
import mysql from 'mysql2/promise';
import cors from 'cors';
import bcrypt from 'bcryptjs';

// --- CONFIGURAÇÃO INICIAL ---
const app = express();
app.use(cors());
app.use(express.json());

// --- CONEXÃO COM O BANCO DE DADOS ---
// Lembre-se de substituir com suas credenciais do MySQL
const db = await mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: 'pedroxp123@',
    database: 'simple_crud_login'
});

// --- ROTAS DA API ---

// ROTA DE TESTE
app.get('/', (req, res) => {
    res.send('API do CRUD de Login está funcionando!');
});

// 1. CREATE (Registrar um novo usuário)
app.post('/register', async (req, res) => {
    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).send('Email e senha são obrigatórios.');
    }

    try {
        // Criptografar a senha
        const hashedPassword = await bcrypt.hash(password, 10);

        // Salvar no banco de dados
        await db.query('INSERT INTO users (email, password) VALUES (?, ?)', [email, hashedPassword]);
        
        res.status(201).send('Usuário registrado com sucesso!');
    } catch (error) {
        // ER_DUP_ENTRY é o código de erro para email duplicado
        if (error.code === 'ER_DUP_ENTRY') {
            return res.status(409).send('Este email já está cadastrado.');
        }
        res.status(500).send('Erro no servidor ao registrar usuário.');
    }
});


// 2. READ (Listar todos os usuários)
app.get('/users', async (req, res) => {
    try {
        const [users] = await db.query('SELECT id, email FROM users');
        res.json(users);
    } catch (error) {
        res.status(500).send('Erro ao buscar usuários.');
    }
});


// 3. UPDATE (Editar o email de um usuário)
app.put('/users/:id', async (req, res) => {
    const { id } = req.params;
    const { email } = req.body;

    if (!email) {
        return res.status(400).send('O email é obrigatório.');
    }

    try {
        await db.query('UPDATE users SET email = ? WHERE id = ?', [email, id]);
        res.send('Usuário atualizado com sucesso.');
    } catch (error) {
        res.status(500).send('Erro ao atualizar usuário.');
    }
});

// 4. DELETE (Excluir um usuário)
app.delete('/users/:id', async (req, res) => {
    const { id } = req.params;
    try {
        await db.query('DELETE FROM users WHERE id = ?', [id]);
        res.send('Usuário deletado com sucesso.');
    } catch (error) {
        res.status(500).send('Erro ao deletar usuário.');
    }
});


// 5. LOGIN (Autenticar um usuário)
app.post('/login', async (req, res) => {
    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).send('Email e senha são obrigatórios.');
    }

    try {
        const [users] = await db.query('SELECT * FROM users WHERE email = ?', [email]);
        
        // Verifica se o usuário existe
        if (users.length === 0) {
            return res.status(404).send('Usuário não encontrado.');
        }

        const user = users[0];

        // Compara a senha enviada com a senha criptografada no banco
        const isPasswordCorrect = await bcrypt.compare(password, user.password);

        if (!isPasswordCorrect) {
            return res.status(401).send('Senha incorreta.');
        }

        res.status(200).send('Login bem-sucedido!');
    } catch (error) {
        res.status(500).send('Erro no servidor durante o login.');
    }
});


// --- INICIAR O SERVIDOR ---
const PORT = 3001;
app.listen(PORT, () => {
    console.log(`🚀 Servidor rodando em http://localhost:${PORT}`);
});