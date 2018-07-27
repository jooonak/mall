package com.worksout.security.dto;

import com.worksout.dto.member.Member;
import com.worksout.dto.member.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityMember extends User {

    private static final long serialVersionUID = 1L;
    private static final String ROLE_PREFIX = "ROLE_";
    private Member member;

    public SecurityMember(Member member) {
        super(member.getUsername(), member.getPassword(), getGrantedAuthorities(member.getRoles()));
        this.member = member;
    }

    private static Collection<? extends GrantedAuthority> getGrantedAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach( r -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + r.getRoleName())) );
        return authorities;
    }

    public Member getMember() {
        return this.member;
    }

    @Override
    public int hashCode() {
        return member.getUsername() != null ? member.getUsername().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof User) {
            return member.getUsername().equals( ( (User) obj).getUsername() );
        }
        return false;
    }

}
