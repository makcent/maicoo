package com.makcent.service.impl;

import com.makcent.common.Const;
import com.makcent.common.ServerResponse;
import com.makcent.common.TokenCache;
import com.makcent.dao.MemberMapper;
import com.makcent.pojo.Member;
import com.makcent.service.IMemberService;
import com.makcent.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public ServerResponse selectQuestion(String username){
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if(!validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String question = memberMapper.selectQuestionByUsername(username);
        if(org.apache.commons.lang3.StringUtils.isNotBlank(question)){
            return ServerResponse.createByErrorMessage(question);
        }

        return  ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    public ServerResponse<String> checkAnswer(String username, String question, String answer){
        int resultCount = memberMapper.checkAnswe(username, question, answer);
        if(resultCount>0){
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    public ServerResponse<String> forgerResetPassword(String username, String password, String forgetToken){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if(!validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }

        if(StringUtils.equals(forgetToken, token)){
            String md5Password = MD5Util.MD5EncodeUtf8(password);
            int rowCount = memberMapper.updatePasswordByUsername(username, md5Password);
            if(rowCount>0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }else{
            return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
        }

        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    public ServerResponse<String> resetPassword(String passOld, String passNew, Member member){
        int resultCount = memberMapper.checkPassword(MD5Util.MD5EncodeUtf8(passOld), member.getMemberId());
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }

        member.setPassword(MD5Util.MD5EncodeUtf8(passNew));
        int updateCount = memberMapper.updateByPrimaryKeySelective(member);
        if(updateCount>0){
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }

        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    public ServerResponse<Member> updateInformation(Member member){
        int resultCount = memberMapper.checkEmailByMemberId(member.getEmail(), member.getMemberId());
        if(resultCount>0){
            return ServerResponse.createByErrorMessage("此邮箱已被注册");
        }
        Member updateMember = new Member();
        updateMember.setMemberId(member.getMemberId());
        updateMember.setEmail(member.getEmail());
        updateMember.setGender(member.getGender());
        updateMember.setNickname(member.getNickname());
        updateMember.setPhone(member.getPhone());
        updateMember.setBirthday(member.getBirthday());

        int updateCount = memberMapper.updateByPrimaryKeySelective(updateMember);
        if (updateCount>0) {
            return ServerResponse.createBySuccess("更新个人信息成功", updateMember);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    public ServerResponse<Member> getInformation(long memberId){
        Member member = memberMapper.selectByPrimaryKey(memberId);
        if(member == null ){
            ServerResponse.createByErrorMessage("找不到当前用户");
        }
        member.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(member);
    }
}
