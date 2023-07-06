package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // JPA의 모든 데이터 변경은 transaction 안에서 실행해야 함

        try {
            /* [CURD]
            생성 - em.persist(member);
            단일 조회 - Member member = em.find(Member.class, 1L);
            수정 - member.setName("HelloJPA"); => transaction commit 시점에 변경감지(Dirty Checking)가 동작함
            삭제 - em.remove(member);
             */

            /* [JPQL]
            - JPQL은 SQL을 추상화한 객체 지향 쿼리 언어, 테이블이 아닌 "객체"를 대상으로 쿼리를 날림
            전체 조회 - List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
             */
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0) // 페이징 (첫번째 row부터)
                    .setMaxResults(10) // 페이징 (10개까지)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
