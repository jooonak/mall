package com.worksout.security.handler;

import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        clearAuthAttr(req);
        strategy.sendRedirect(req, res, defineSuccessUrl(req, auth));

    }

    private String defineSuccessUrl(HttpServletRequest req, Authentication auth) {
        HttpSession session = req.getSession(false);
        String prev = (String) session.getAttribute("prev");
        session.removeAttribute("prev");
        boolean isAdmin = false;

        for (GrantedAuthority role : auth.getAuthorities()) {
            log.info(role.getAuthority());
            if ("ROLE_ADMIN".equals(role.getAuthority())) {
                isAdmin = true;
                break;
            }
        }

        if ( isAdmin ) {
            return "/admin/";
        } else {
            return prev;
        }
    }

    private void clearAuthAttr(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        log.info("AUTHENTICATION EXCEPTION : " + session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
