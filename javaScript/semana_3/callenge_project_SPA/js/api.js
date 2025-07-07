const BASE_URL = "http://localhost:3000/users";

// Get all users
export async function getUsers() {
    const res = await fetch(BASE_URL);
    return res.json();
}

// Create a new user
export async function createUser(user) {
    const res = await fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
    });
    return res.json();
}

// Update user
export async function updateUser(id, user) {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
    });
    return res.json();
}

// Delete user
export async function deleteUser(id) {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE",
    });
    return res.ok;
}