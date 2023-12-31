const express = require('express');
const userController = require('../controllers/userController.js');

const router = express.Router();

router.get('/users', userController.getAllUsers);

// The POST route to create a new user
router.post('/users', userController.createUser);

// Export the router at the end
module.exports = router;
