package com.parkging.blog.apiapp.global.oauth;

import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.domain.member.domain.MemberRole;
import com.parkging.blog.apiapp.domain.member.service.MemberService;
import com.parkging.blog.apiapp.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;

    /**
     * 구글에서 받은 userRequest 데이터에 대한 후처리 함수
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //registrationId로 어떤 OAuth 로 로그인 했는지 판별
        System.out.println("userRequest.getClientRegistration() = " + userRequest.getClientRegistration());
        System.out.println("userRequest.getAccessToken() = " + userRequest.getAccessToken());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Client라이브러리) -AccessToken 요청
        //userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필 받는다.
        System.out.println("getAttributes() = " + oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getClientId();  //google
        String prividerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String password = bCryptPasswordEncoder.encode("password");
        MemberRole memberRole = MemberRole.ROLE_USER;

        Member member;

        try {
            member = memberService.findByEmail(email);
        } catch (NoResultException e) {
            memberService.join(name, email, password, password, memberRole);
            member = memberService.findByEmail(email);
        }

        return new PrincipalDetails(member, (Map<String, Object>) oAuth2User.getAuthorities());
    }
}
