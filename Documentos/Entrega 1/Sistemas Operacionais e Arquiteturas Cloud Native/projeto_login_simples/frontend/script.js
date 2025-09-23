// frontend/script.js

document.addEventListener('DOMContentLoaded', () => {
    const API_URL = 'http://localhost:3001';

    const registerForm = document.getElementById('registerForm');
    const loginForm = document.getElementById('loginForm');
    const usersList = document.getElementById('usersList');

    // Função para buscar e exibir os usuários
    const fetchUsers = async () => {
        try {
            const response = await fetch(`${API_URL}/users`);
            const users = await response.json();

            usersList.innerHTML = ''; // Limpa a lista antes de adicionar os itens
            users.forEach(user => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <span>${user.email}</span>
                    <div class="actions">
                        <button class="edit-btn" data-id="${user.id}" data-email="${user.email}">Editar</button>
                        <button class="delete-btn" data-id="${user.id}">Deletar</button>
                    </div>
                `;
                usersList.appendChild(li);
            });
        } catch (error) {
            console.error('Erro ao buscar usuários:', error);
        }
    };

    // Event listener para registro
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('registerEmail').value;
        const password = document.getElementById('registerPassword').value;

        const response = await fetch(`${API_URL}/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        const message = await response.text();
        alert(message);
        if (response.ok) {
            registerForm.reset();
            fetchUsers();
        }
    });

    // Event listener para login
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        const response = await fetch(`${API_URL}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        const message = await response.text();
        alert(message);
        if (response.ok) {
            loginForm.reset();
        }
    });

    // Event listeners para os botões de editar e deletar (usando delegação de eventos)
    usersList.addEventListener('click', async (e) => {
        const target = e.target;
        const id = target.dataset.id;

        // Ação de Deletar
        if (target.classList.contains('delete-btn')) {
            if (confirm('Tem certeza que deseja deletar este usuário?')) {
                const response = await fetch(`${API_URL}/users/${id}`, { method: 'DELETE' });
                const message = await response.text();
                alert(message);
                if (response.ok) {
                    fetchUsers();
                }
            }
        }
        
        // Ação de Editar
        if (target.classList.contains('edit-btn')) {
            const currentEmail = target.dataset.email;
            const newEmail = prompt('Digite o novo e-mail:', currentEmail);

            if (newEmail && newEmail !== currentEmail) {
                const response = await fetch(`${API_URL}/users/${id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ email: newEmail })
                });
                const message = await response.text();
                alert(message);
                if (response.ok) {
                    fetchUsers();
                }
            }
        }
    });

    // Carrega os usuários ao iniciar a página
    fetchUsers();
});