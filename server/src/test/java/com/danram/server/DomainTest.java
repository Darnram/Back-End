package com.danram.server;

import com.danram.server.domain.Authority;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class DomainTest {
    private final MemberRepository memberRepository;
    private final DateLogRepository dateLogRepository;

    @Autowired
    public DomainTest(final MemberRepository memberRepository, final DateLogRepository dateLogRepository) {
        this.memberRepository = memberRepository;
        this.dateLogRepository = dateLogRepository;
    }

    @Test
    public void domain_test() {
        //given
        Authority user = new Authority("ROLE_USER");
        Authority admin = new Authority("ROLE_ADMIN");

        Long id = System.currentTimeMillis();

        DateLog dateLog = DateLog.builder()
                .memberId(id)
                .createdAt(LocalDate.now())
                .build();

        final DateLog save = dateLogRepository.save(dateLog);

        Member member = Member.builder()
                .memberId(id)
                .authorities(List.of(user, admin))
                .ban(false)
                .email("djs9844@naver.com")
                .img("https://img3.daumcdn.net/thumb/R658x0.q70/?fname=https://t1.daumcdn.net/news/202301/03/startoday/20230103103009408tnvd.jpg")
                .loginType(0L)
                .nickname("지승언")
                .pro(true)
                .build();

        //when
        final Member member1 = memberRepository.save(member);

        //then
        Assertions.assertThat(member1.getMemberId()).isEqualTo(id);
    }
}
