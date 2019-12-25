package dao;

import obj.Question;

import java.util.ArrayList;

public interface QuestionDao extends Dao
{
    boolean addQuestion(Question question);

    ArrayList<Question> findByContext(String context);

    ArrayList<Question> findByCur(String cur_name);

    ArrayList<Question> findByTeacher(String te_name);

    ArrayList<Question> findAll();

    boolean delQuestion(int qid);

    boolean updateQuestion(Question question);

    int findMxId();
}
