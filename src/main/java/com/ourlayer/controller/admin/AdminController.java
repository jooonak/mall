package com.ourlayer.controller.admin;

import com.ourlayer.security.dto.SecurityMember;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Log
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String main (Model model, HttpServletRequest req, Authentication auth) {
        SecurityMember member = (SecurityMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("say", "HELLO!!");
        log.info("IS SEASON ATTR EXISTS? : " + req.getSession(false).getAttribute("season"));
        log.info("");
        log.info("LAST ACCESSED TIME : " + new Date(req.getSession().getLastAccessedTime()));
        log.info("");
        log.info("Member : " + member.getUsername());
        log.info("");
        log.info("Member ROLES : " + member.getAuthorities());
        log.info("");
        log.info("AUTH : " + auth.getAuthorities());
        log.info("");
        log.info("IS USER BASIC ? : " + req.isUserInRole("BASIC"));
        log.info("");
        log.info("IS USER ADMIN ? : " + req.isUserInRole("ADMIN"));
        return "/admin/main";
    }

}