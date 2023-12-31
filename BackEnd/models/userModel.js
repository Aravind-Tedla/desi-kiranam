const mysql = require('mysql2');
require('dotenv').config();

// Access environment variables
const dbHost = process.env.DB_HOST;
const dbUser = process.env.DB_USER;
const dbPassword = process.env.DB_PASSWORD;
console.log("dbHost:", dbHost);
console.log("dbUser:", dbUser);
console.log("dbPassword:", dbPassword);
const dbConfig = {
    host: dbHost, 
    user: dbUser,
    password: dbPassword,      
    database: 'desi_kiranam', 
    port: 3306,        
    connectTimeout: 100000 
};


const connection = mysql.createConnection(dbConfig);



// Error handling
connection.on('error', (err) => {
    console.error('MySQL connection error: ', err);
});


module.exports = connection;
