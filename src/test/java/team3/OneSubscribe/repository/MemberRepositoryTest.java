package team3.OneSubscribe.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team3.OneSubscribe.domain.Member;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepositoryImp memberRepository;

    @Test
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
//        member.setUsername("memberA");
        member.setNickName("memberA");

        //when
        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
        Member findMember = memberRepository.findOneById(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember.getNickName()).isEqualTo(member.getNickName());
    }
}