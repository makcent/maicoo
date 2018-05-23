package com.makcent.dao;

import com.makcent.pojo.QuestionAnswer;

public interface QuestionAnswerMapper {
    int deleteByPrimaryKey(Integer answerId);

    int insert(QuestionAnswer record);

    int insertSelective(QuestionAnswer record);

    QuestionAnswer selectByPrimaryKey(Integer answerId);

    int updateByPrimaryKeySelective(QuestionAnswer record);

    int updateByPrimaryKeyWithBLOBs(QuestionAnswer record);

    int updateByPrimaryKey(QuestionAnswer record);
}