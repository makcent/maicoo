package com.makcent.controller.portal;

import com.makcent.common.Const;
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
}
