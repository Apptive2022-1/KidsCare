package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImp implements MemberRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // return 필요?
    }

    @Override
    public Member findOneById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public Member findByNickName(String nickName) {
        return em.createQuery("select m from Member m where m.nickName = : nickName", Member.class)
                .setParameter("nickName", nickName)
                .getSingleResult();
    }

    ;

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


    @Override
    //일치하는게 없으면 null반환.
    public Member findByLoginId(String loginId) {
        List<Member> li = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
        if (li.size() == 0)
            return null;
        else
            return li.get(0);
    }

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<Member> findBestExpert() {
        List<Member> li = em.createQuery("select m from Member m order by m.totalLikeNumber desc", Member.class)
                .getResultList();
        if (li.isEmpty()) return li;

        List<Member> bestexperts = new LinkedList<>();
        if (li.size() > 1) {
            bestexperts.add(li.get(0));
        }
        if (li.size() > 2) {

            bestexperts.add(li.get(1));
        }
        if (li.size() > 3) {

            bestexperts.add(li.get(2));
        }
        return bestexperts;

    }

    @Override
    public List<Member> findBestInexpert() {
        List<Member> li = em.createQuery("select m from Member m order by m.totalLikeNumber desc", Member.class)
                .getResultList();
        if (li.isEmpty()) return li;

        List<Member> bestInexperts = new LinkedList<>();
        if (li.size() > 1) {

            bestInexperts.add(li.get(0));
        }
        if (li.size() > 2) {

            bestInexperts.add(li.get(1));
        }
        if (li.size() > 3) {
            bestInexperts.add(li.get(2));

        }
        return bestInexperts;
    }


}

