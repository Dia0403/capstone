// server.js
require('dotenv').config();

const express = require('express');
const connectDB = require('./config/db');
const authRoutes = require('./routes/auth');
const app = express();
const cors = require('cors');
app.use(cors());


connectDB();

app.use(express.json());
app.use('/api/auth', authRoutes);

app.listen(5000, () => console.log('✅ 서버 실행 중: http://localhost:5000'));
