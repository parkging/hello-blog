package com.parkging.helloblog.domain.member.application;public class MemberSearchService{private final com.parkging.helloblog.repository.MemberRepository memberRepository;	public MemberSearchService()	{	}@org.springframework.transaction.annotation.Transactional
    public java.lang.Long join(java.lang.String name, java.lang.String email, java.lang.String password) {
        com.parkging.helloblog.domain.Member member = com.parkging.helloblog.domain.Member.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();

        return memberRepository.save(member).getId();
    }public com.parkging.helloblog.domain.Member findById(java.lang.Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }public com.parkging.helloblog.domain.Member findByEmail(java.lang.String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }}