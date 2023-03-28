package com.parkging.blog.apiapp.domain.member.api;

import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.domain.member.dto.LoginDto;
import com.parkging.blog.apiapp.domain.member.dto.LoginResult;
import com.parkging.blog.apiapp.domain.member.dto.MemberDto;
import com.parkging.blog.apiapp.domain.member.dto.SginUpDto;
import com.parkging.blog.apiapp.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("members")
    public Long join(@RequestBody @Validated SginUpDto sginUpDto) {
        return memberService.join(sginUpDto.getName(),
                                sginUpDto.getEmail(),
                                sginUpDto.getPassword(),
                                sginUpDto.getPasswordConfirm());
    }

    @GetMapping("members/{memberId}")
    public MemberDto getMember(@PathVariable Long memberId) {
        Member member = memberService.findById(memberId);
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    @PostMapping("certification")
    public LoginResult login(@RequestBody @Validated LoginDto loginDto) {
        Member member = memberService.login(loginDto.getEmail(), loginDto.getPassword());
        /* todo api 통신에서 세션을 사용할 수 없으므로 토큰방식으로 인증로직 추가 필요 Start */
//        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        //세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        /* todo api 통신에서 세션을 사용할 수 없으므로 토큰방식으로 인증로직 추가 필요 End */

        return LoginResult.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    @DeleteMapping("certification")
    public void logout() {
        /* todo 로그아웃 기능 추가 필요 */
    }

}
