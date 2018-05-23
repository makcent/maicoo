package com.makcent.service;

import com.makcent.common.ServerResponse;
import com.makcent.pojo.Member;

public interface IMemberService {

    ServerResponse<Member> login(String username, String password);

    ServerResponse<String> register(Member member);

    ServerResponse<String> checkValid(String str, String type);

}
