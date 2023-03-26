package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Member;
import com.parkging.helloblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     *
     * @return null이면 login 실패
     */
    public Member login(String loginEmail, String password) {
        return memberRepository.findByEmail(loginEmail)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
