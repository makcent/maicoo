package com.makcent.dao;

import com.makcent.pojo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    int deleteByPrimaryKey(Long memberId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long memberId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    int checkMemberUsername(String username);

    int checkEmail(String email);

    Member selectLogin(@Param("username") String username, @Param("password") String password);


}




































