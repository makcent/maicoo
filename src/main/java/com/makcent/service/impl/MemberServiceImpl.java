package com.makcent.service.impl;

import com.makcent.common.Const;
import com.makcent.common.ServerResponse;
import com.makcent.dao.MemberMapper;
import com.makcent.pojo.Member;
import com.makcent.service.IMemberService;
import com.makcent.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iMemberService")
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public ServerResponse<Member> login(String username, String password) {
        int resultCount = memberMapper.checkMemberUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        Member member = memberMapper.selectLogin(username, md5Password);
        if(member == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        member.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", member);
    }

    public ServerResponse<String> register(Member member){
        ServerResponse validResponse = this.checkValid(member.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkValid(member.getEmail(), Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        member.setRole(Const.Role.ROLE_CUSTOMER);
        member.setPassword(MD5Util.MD5EncodeUtf8(member.getPassword()));
        int resultCount = memberMapper.insert(member);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    public ServerResponse<String> checkValid(String str, String type){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){

            if(Const.USERNAME.equals(type)){
                int resultCount = memberMapper.checkMemberUsername(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = memberMapper.checkEmail(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("电子邮箱已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return ServerResponse.createBySuccessMessage("校验成功");
    }
}
