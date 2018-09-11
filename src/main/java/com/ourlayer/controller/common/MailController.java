package com.ourlayer.controller.common;

import com.ourlayer.common.Mailer;
import com.ourlayer.dto.member.PasswordResetToken;
import com.ourlayer.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private Mailer mailer;

    @Autowired
    private MemberService memberService;

    @GetMapping("/password-token")
    public String passwordToken(PasswordResetToken token, Model model) {
        PasswordResetToken resetToken = memberService.getToken(token);
        model.addAttribute("resetToken", resetToken);
        return "/common/password-token";
    }

}
