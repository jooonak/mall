package com.worksout.service.member;

import com.worksout.common.Mailer;
import com.worksout.dto.member.Member;
import com.worksout.dto.member.PasswordResetToken;
import com.worksout.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

@Service
public class MemberService {

	@Autowired
	Mailer mailSender;

    @Autowired
    MemberMapper memberMapper;

    @Transactional
    public void regMember(Member member) {
        memberMapper.regMember(member);
        memberMapper.regMemberRole(member);
    }

    public Member findMember(String username) {
        return memberMapper.findMember(username);
    }

    public void createPasswordResetToken(Member member) {
	    String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, member);

        memberMapper.saveToken(resetToken);

	    mailSender.sendToken(
			    member.getUsername(), "RESETTING YOUR PASSWORD", token);
    }

    public PasswordResetToken getToken(PasswordResetToken token) {
        return memberMapper.getToken(token);
    }

    public boolean validatePasswordResetToken(Member member, String token) {
        PasswordResetToken storedToken = memberMapper.getToken(new PasswordResetToken(token, member));
	    Calendar calendar = Calendar.getInstance();
	    boolean result = true;

	    if ( storedToken.getToken() == null || !storedToken.getMember().getUsername().equals(member.getUsername())) {
	    	// 해당 토큰이 없거나 토큰에 해당하는 멤버 정보가 없을 때
		    result = false;
	    } else {
		    if (storedToken.getExpiryDate().getTime() < calendar.getTime().getTime()) {
		    	// 현재 시간과 토큰의 유효기간 비교
			    result = false;
		    }
	    }
	    /*
	     * Member member = storedToken.getMember();
	     * 권한 구현 후 change_password 권한 부여 해야함
	     */
        return result;
    }

	public void updatePassword(Member member) {
		memberMapper.updatePassword(member);
	}

}
