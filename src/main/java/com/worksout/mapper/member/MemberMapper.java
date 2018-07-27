package com.worksout.mapper.member;

import com.worksout.dto.member.Member;
import com.worksout.dto.member.PasswordResetToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO MEM_INFO (USERNAME, NAME, PASSWORD) VALUES (#{username}, #{name}, #{password})")
    void regMember(Member member);

    @Insert("INSERT INTO MEM_ROLE (USERNAME, ROLE_ID, REG_DT) VALUES (#{username}, 1, NOW())")
    void regMemberRole(Member member);

    Member findMember(String username);

    void saveToken(PasswordResetToken token);

    PasswordResetToken getToken(PasswordResetToken token);

    @Update("UPDATE MEM_INFO SET PASSWORD = #{password}, UPD_DT = NOW() WHERE USERNAME = #{username}")
	void updatePassword(Member member);

}
