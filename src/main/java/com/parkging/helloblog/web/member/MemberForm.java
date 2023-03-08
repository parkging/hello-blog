package com.parkging.helloblog.web.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    @NotEmpty
    private String name;
    @NotEmpty @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordConfirm;
}
