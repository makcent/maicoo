package com.makcent.service;

import com.makcent.common.ServerResponse;
import com.makcent.pojo.Member;

public interface IMemberService {

    ServerResponse<Member> login(String username, String password);

    ServerResponse<String> register(Member member);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgerResetPassword(String username, String password, String forgetToken);

    ServerResponse<String> resetPassword(String passOld, String passNew, Member member);

    ServerResponse<Member> updateInformation(Member member);

    ServerResponse<Member> getInformation(long memberId);
}
