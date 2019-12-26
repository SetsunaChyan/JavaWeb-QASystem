package dao;

import obj.Question;

import java.util.ArrayList;

public interface QuestionDao extends Dao
{
    boolean addQuestion(Question question);

    ArrayList<Question> findByContext(String context);

    ArrayList<Question> findByCur(String cur_name);

    ArrayList<Question> findByTeacher(String te_name);

    ArrayList<Question> findByUsername(String username);

    Question findById(int qid);

    ArrayList<Question> findAll();

    boolean delQuestion(int qid);

    boolean updateQuestion(Question question);

    int findByStudentCnt(String stu_name);

    int findByTeacherCnt(String te_name);

    int findMxId();
}
