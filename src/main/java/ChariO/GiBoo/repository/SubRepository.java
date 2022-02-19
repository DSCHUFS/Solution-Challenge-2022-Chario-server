package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubRepository {

    private final EntityManager em;


    /**
     * 전체조회
     * @return 모든 구독 리스트
     */
    public List<Subscribe> findAll() {
        return em.createQuery("select s from Subscribe s", Subscribe.class)
                .getResultList();
    }

    public List<Subscribe> findByFacId(Long id){
        return em.createQuery("select s from Subscribe s" +
                " join fetch s.user u" +
                " join fetch s.facility f" +
                " where s.facility.id = :id ", Subscribe.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void save(Subscribe subscribe) {
        em.persist(subscribe);
    }

    /**
     * @param id
     * @return 일치하는 ID의 구독 리스트
     * 별 필요 없음
     */
    public Subscribe findById(Long id) {
        return em.find(Subscribe.class, id);
    }


    /**
     * @param user
     * @return 일치하는 user의 구독 기관 리스트
     */
    public Subscribe findByUser(User user) {
        return em.find(Subscribe.class, user);
    }

    public void delete(Subscribe subscribe){
        em.remove(subscribe);
    }

    public Subscribe findByUserFac(Long u_id, Long f_id){
        return em.createQuery("select s from Subscribe s " +
                "where s.user.id =: u_id " +
                "and s.facility.id =: f_id ", Subscribe.class)
                .setParameter("u_id", u_id)
                .setParameter("f_id", f_id)
                .getSingleResult();
    }
}
