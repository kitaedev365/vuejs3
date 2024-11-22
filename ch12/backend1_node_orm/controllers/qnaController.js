const Qna = require('../models/Qna');

// 질문 목록 조회
const list = async (req, res, next) => {
  try {
    const qnas = await Qna.findAll();
    res.json(qnas);
  } catch (error) {
    next(error);
  }
};

// 질문 상세 조회
const detail = async (req, res, next) => {
  try {
    const qno = req.params.qno;
    const qna = await Qna.findByPk(qno);
    if (!qna) {
      return res.status(404).json({ message: 'QNA not found' });
    }
    res.json(qna);
  } catch (error) {
    next(error);
  }
};

// 질문 작성
const create = async (req, res, next) => {
  try {
    const { title, content, author } = req.body;
    const newQNA = await Qna.create({
      title,
      content,
      author
    });
    res.json(newQNA);
  } catch (error) {
    next(error);
  }
};

// 답변 조회
const getAnswer = async (req, res, next) => {
  try {
    const qno = req.params.qno;
    const answer = await Qna.findAll({ where: { parno: qno } });
    if (!answer) {
      return res.status(404).json({ message: 'Answer not found' });
    }
    res.json(answer);
  } catch (error) {
    next(error);
  }
};

// 질문 수정
const update = async (req, res, next) => {
  try {
    const qno = req.params.qno;
    const updatedQNA = req.body;
    await Qna.update(updatedQNA, { where: { qno } });
    res.json({ message: 'QNA updated successfully' });
  } catch (error) {
    next(error);
  }
};

// 질문 삭제
const deleteQna = async (req, res, next) => {
  try {
    const qno = req.params.qno;
    await Qna.destroy({ where: { qno } });
    res.json({ message: 'QNA deleted successfully' });
  } catch (error) {
    next(error);
  }
};

module.exports = {
  list,
  detail,
  create,
  getAnswer,
  update,
  delete: deleteQna
};