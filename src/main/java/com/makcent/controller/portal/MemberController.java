package com.makcent.controller.portal;

import com.makcent.common.Const;
import com.makcent.common.ResponseCode;
import com.makcent.common.ServerResponse;
import com.makcent.pojo.Member;
import com.makcent.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    private IMemberService iMemberService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Member> login(String username, String password, HttpSession session)
    {
        ServerResponse<Member> response = iMemberService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_MEMBER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_MEMBER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> register(Member member){
        return iMemberService.register(member);
    }

    @RequestMapping(value = "check_valid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type){
        return iMemberService.checkValid(str, type);
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Member> getUserInfo(HttpSession session){
        Member member = (Member) session.getAttribute(Const.CURRENT_MEMBER);
        if(member != null){
            return ServerResponse.createBySuccess(member);
        }
        return ServerResponse.createBySuccessMessage("用户未登录，无法获取当前用户信息");
    }

    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iMemberService.selectQuestion(username);
    }

    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer){
        return iMemberService.checkAnswer(username, question, answer);
    }

    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String password, String forgetToken){
        return iMemberService.forgerResetPassword(username,password, forgetToken);
    }

    @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passOld, String passNew){
        Member member = (Member)session.getAttribute(Const.CURRENT_MEMBER);
        if(member == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iMemberService.resetPassword(passOld, passNew,member);
    }

    @RequestMapping(value = "update_infomation.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Member> update_infomation(HttpSession session, Member member){
        Member currentMember = (Member)session.getAttribute(Const.CURRENT_MEMBER);
        if(currentMember == null){
            return  ServerResponse.createByErrorMessage("用户未登陆");
        }
        member.setMemberId(currentMember.getMemberId());
        member.setUsername(currentMember.getUsername());
        ServerResponse<Member> response = iMemberService.updateInformation(member);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_MEMBER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "get_information.do", method = RequestMethod.GET)
    @ResponseBody
    public  ServerResponse<Member> get_information(HttpSession session){
        Member currentMember = (Member)session.getAttribute(Const.CURRENT_MEMBER);
        if(currentMember == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要登录");
        }
        return iMemberService.getInformation(currentMember.getMemberId());
    }
}
