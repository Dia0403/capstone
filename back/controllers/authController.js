// controllers/authController.js
const User = require('../models/User');
const bcrypt = require('bcrypt');

const registerUser = async (req, res) => {
  try {
    const { username, password } = req.body;
    const hashedPw = await bcrypt.hash(password, 10);
    const newUser = new User({ username, password: hashedPw });
    await newUser.save();
    res.status(201).json({ message: '회원가입 성공' });
  } catch (err) {
    res.status(400).json({ error: '회원가입 실패', details: err.message });
  }
};

const loginUser = async (req, res) => {
  try {
    const { username, password } = req.body;
    const user = await User.findOne({ username });
    if (!user) return res.status(404).json({ error: '존재하지 않는 유저' });

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) return res.status(401).json({ error: '비밀번호 틀림' });

    res.json({ message: '로그인 성공' });
  } catch (err) {
    res.status(500).json({ error: '로그인 오류', details: err.message });
  }
};

module.exports = { registerUser, loginUser };
