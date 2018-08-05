package com.worksout.controller;

import com.worksout.dto.member.Member;
import com.worksout.dto.member.PasswordResetToken;
import com.worksout.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/sign-in")
    public void signIn(HttpServletRequest req) {
        if ( null != req.getHeader("Referer") && null != req.getSession(false) ) {
            req.getSession(false).setAttribute("prev", req.getHeader("Referer"));
        }
    }

    @GetMapping("/sign-up")
    public void signUp() { }

    @PostMapping("/sign-up")
    public String signUp(Member member) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            memberService.regMember(member);

            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/sign-up?fail";
        }
    }

    @GetMapping("/forgot-password")
    public void forgotPassword() { }

    @PostMapping("/forgot-password")
    public String forgotPassword(String username, RedirectAttributes rttr) {

        Member member = memberService.findMember(username);
        if ( member == null ) {
            rttr.addFlashAttribute("message", "Could not found user for username : " + username);
            return "redirect:/forgot-password?fail";
        }

        try {
            memberService.createPasswordResetToken(member);
            rttr.addFlashAttribute("message", "Successfully sent mail! please check your e-mail");
            return "redirect:/sign-in";
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("message", "Fail to send mail. please try again");
            return "redirect:/forgot-password?fail";
        }
    }

    @GetMapping("/update-password")
    public String updatePassword(Member member, String token, RedirectAttributes rttr, Model model) {
//        if (memberService.validatePasswordResetToken(member, token)) {
            model.addAttribute("userInfo", new PasswordResetToken(token, member));
            return null;
//        }
//        rttr.addFlashAttribute("updateResult","Expired or InvalidToken");
//        return "redirect:/login";
    }

    @PostMapping("/update-password")
    public String updatePassword(Member member) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            memberService.updatePassword(member);

            return "redirect:/sign-in?updateSuccess";
        } catch (Exception e) {
            return "redirect:/update-password?fail";
        }
    }

}
