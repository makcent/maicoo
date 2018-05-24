package com.makcent.controller.backend;

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
@RequestMapping("/mamage/member")
public class MemberManageController {

    @Autowired
    private IMemberService iMemberService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Member> login(String username, String password, HttpSession session){
        ServerResponse<Member> response = iMemberService.login(username, password);
        if(response.isSuccess()){
            Member member  = response.getData();
            if(member.getRole() == Const.Role.ROLE_ADMIN){
                session.setAttribute(Const.CURRENT_MEMBER, member);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员无法登陆");
            }
        }
        return response;
    }
}
