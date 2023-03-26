package com.parkging.helloblog.service;

import com.parkging.helloblog.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private EntityManager em;

    @Test
    @Rollback(value = false)
    public void 회원가입() {

        String name = new String("박준영");
        String email = String.valueOf("parkjun611@gmail.com");
        String password = String.valueOf("dkansk");

        Long savedId = memberService.join(name, email, password);

        Assertions.assertThat(savedId).isEqualTo(memberService.findById(savedId).getId());

    }

    @Test
    public void 페이징테스트() {

        int pageSize = 5;

        int currentPage = 1;
        int startPage = (currentPage-1 / pageSize) * pageSize + 1;
        int endPage = startPage + pageSize;

        currentPage = 1;
        startPage = ((currentPage-1) / pageSize) * pageSize + 1;
        endPage = startPage + pageSize - 1;
        System.out.println("currentPage = " + currentPage + ", startPage = " + startPage + ", endPage = " + endPage);

        currentPage = 5;
        startPage = ((currentPage-1) / pageSize) * pageSize + 1;
        endPage = startPage + pageSize - 1;
        System.out.println("currentPage = " + currentPage + ", startPage = " + startPage + ", endPage = " + endPage);

        currentPage = 6;
        startPage = ((currentPage-1) / pageSize) * pageSize + 1;
        endPage = startPage + pageSize - 1;
        System.out.println("currentPage = " + currentPage + ", startPage = " + startPage + ", endPage = " + endPage);


    }

}