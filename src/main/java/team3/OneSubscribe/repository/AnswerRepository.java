package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {

    @PersistenceContext
    private EntityManager em;

    public long save(Answer answer){
        em.persist(answer);
        return answer.getId();
    }

    public Answer findOneById(Long id){
        return em.find(Answer.class, id);
    }

    public Answer findOneByNickName(String nickName){
        return em.find(Answer.class, nickName);
    }

    public List<Answer> findAll(){
        return em.createQuery("select i from Answer i", Answer.class)
                .getResultList();
    }

    public List<Answer> findByWriting(Writing writing){
        return em.createQuery("select i from Answer i where i.writing = :writing", Answer.class)
                .setParameter("writing", writing)
                .getResultList();
    }

    // writing의 answer 수 제공하기
    public int findAnswerNumberByWriting(Writing w){
        List<Answer> answers =  em.createQuery("select i from Answer i where i.writing = :writing", Answer.class)
                .setParameter("writing", w)
                .getResultList();

        return answers.size();
    }

}
