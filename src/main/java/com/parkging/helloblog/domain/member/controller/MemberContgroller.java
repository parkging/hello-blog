package com.parkging.helloblog.web.member;

import com.parkging.helloblog.domain.Member;
import com.parkging.helloblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberContgroller {

    private final MemberService memberService;

//    @GetMapping("signin")
    public String addMember(@ModelAttribute MemberForm memberForm) {
        return "member/add-member";
    }

//    @PostMapping("signin")
    public String addMember(@Valid @ModelAttribute MemberForm form, BindingResult bindingResult) {

        if (!validationCheck(form, bindingResult)) {
            return "member/add-member";
        }

        memberService.join(form.getName(), form.getEmail(), form.getPassword());

        return "redirect:/";
    }

    private boolean validationCheck(MemberForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return false;
        }

        if (isNotPasswordAccord(form.getPassword(), form.getPasswordConfirm())) {
            bindingResult.reject("passwordConfirmFail", "패스워드가 일치하지 않습니다.");
            return false;
        }

        Member findMember = memberService.findByEmail(form.getEmail());
        if (findMember != null) {
            bindingResult.reject("emailDuplicationError", "이미 존재하는 메일주소 입니다.");
            return false;
        }
        return true;
    }

    private static boolean isNotPasswordAccord(String password, String passwordConfirm) {
        return !password.equals(passwordConfirm);
    }
}
