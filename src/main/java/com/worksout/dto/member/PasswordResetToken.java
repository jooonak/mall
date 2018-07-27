package com.worksout.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PasswordResetToken {

    @NonNull private String token;
    @NonNull private Member member;
    private Date expiryDate;

}
