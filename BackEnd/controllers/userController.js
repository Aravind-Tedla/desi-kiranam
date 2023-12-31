const connection = require('../models/userModel.js');
const bcrypt = require('bcrypt');
const saltRounds = 10; // You can adjust this number for more security, but it will be slower.

exports.signupUser = (req, res) => {
    const { first_name, last_name, email, banner_id, username, password } = req.body;
    console.log("Request Body received:", req.body);
    console.log("Password:", password);
    // Hash the password
    bcrypt.hash(password, saltRounds, function(err, hashedPassword) {
        if (err) {
            console.error(err);
            return res.status(500).send('Server error during password hashing');
        }

        // Insert the new user into the database with the hashed password
        const query = 'INSERT INTO users (first_name, last_name, email, banner_id, username, password) VALUES (?, ?, ?, ?, ?, ?)';
        connection.query(query, [first_name, last_name, email, banner_id, username, hashedPassword], (error) => {
            if (error) {
                console.error(error);
                res.status(500).send('Server error during user registration');
            } else {
                res.send('Signup successful');
            }
        });
    });
};


exports.loginUser = (req, res) => {
    const { email, password } = req.body;
    console.log("Request body received:", req.body);
    console.log("Email received:", email);

    connection.query('SELECT * FROM users WHERE email = ?', [email], (error, results) => {
        if (error) {
            console.error(error);
            return res.status(500).send('Server error');
        }
        
        if (results.length === 0) {
            // No user found with the given email
            return res.status(401).json({ message: 'No user available with provided email' });
        }

        // At this point, we have a user, so we can try to compare passwords.
        const user = results[0];
        bcrypt.compare(password, user.password, (err, isMatch) => {
            if (err) {
                console.error(err);
                return res.status(500).send('Server error during password comparison');
            }
            
            if (isMatch) {
                // If the password matches, redirect to the homepage
                // res.status(200).json({ message: 'Login successful' });
                return res.status(200).json({ message: 'Login successful', redirectUrl: 'http://localhost:3000/index.html' });
                // res.redirect('/index.html')

                
            } else {
                // If the password does not match, return an error
                return res.status(401).json({ message: 'Wrong Password' });
            }
        });
    });
};
