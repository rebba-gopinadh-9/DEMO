window.onload = function() {
    // If the user is on the login page, there's no need to do anything.
    // The server will handle token validation when requesting protected pages.
};

// Login form submission handling and redirection
document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent default form submission

    const mail = document.getElementById('mail').value;
    const password = document.getElementById('password').value;

    const response = await fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({ mail, password }),
        credentials: 'include' // Include credentials (cookies)
    });

    const result = await response.json();

    if (response.ok) {
        // Manually set the cookie
        const token = result.token; // Assuming your server responds with a token
        const expires = new Date(Date.now() + 60 * 60 * 1000).toUTCString(); // Set expiration to 1 hour from now
        
        // Setting the cookie manually
        document.cookie = `token=${token}; expires=${expires}; path=/; secure; HttpOnly; SameSite=Strict`;

        window.location.href = '/home-page'; // Redirect to /home after successful login
    } else {
        alert(result.message); // Show error message if login fails
    }
});
