const express = require('express');
const app = express();
const PORT = 3000;
const bodyParser = require('body-parser');
const userController = require('./controllers/userController');
const cors = require('cors'); // Import the cors middleware

// Enable CORS for all routes
app.use(cors());

app.use(express.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
app.use(express.json()); // for parsing application/json

// Middleware to serve static files
app.use(express.static('public'));

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/public/Homepage/homepage.html');
});

app.post('/login', userController.loginUser);

app.post('/user/signupUser', userController.signupUser);

app.listen(PORT, () => {
    console.log(`Server started on http://localhost:${PORT}`);
});







