package com.worksout.security.service;

import com.worksout.mapper.member.MemberMapper;
import com.worksout.security.dto.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(mapper.findMember(username))
                .filter( m -> m != null )
                .map( m -> new SecurityMember(m) ).get();
    }

}
