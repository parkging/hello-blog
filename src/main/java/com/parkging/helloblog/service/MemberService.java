package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Member;
import com.parkging.helloblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(String name, String email, String password) {
        Member member = Member.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();

        return memberRepository.save(member).getId();
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }
}
