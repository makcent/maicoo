package com.makcent.dao;

import com.makcent.pojo.QuestionReward;
import com.makcent.pojo.QuestionRewardKey;

public interface QuestionRewardMapper {
    int deleteByPrimaryKey(QuestionRewardKey key);

    int insert(QuestionReward record);

    int insertSelective(QuestionReward record);

    QuestionReward selectByPrimaryKey(QuestionRewardKey key);

    int updateByPrimaryKeySelective(QuestionReward record);

    int updateByPrimaryKey(QuestionReward record);
}