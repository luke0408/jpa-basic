package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaFlush {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // JPA의 모든 데이터 변경은 transaction 안에서 실행해야 함

        try {
            /* [Flush]
            - 영속성 컨텍스트를 비우지 않음
            - 영속성 컨텍스트의 변경 내용을 DB에 동기화
            - 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화하면 됨
            * */

            Member member1 = new Member(200L, "member200");
            em.persist(member1);

            // 영속성 컨텍스트의 변경 내용을 DB에 반영, commit 시점이 아닌 flush를 호출하는 시점에 DB에 Query가 날라감
            em.flush(); // 이때 1차 캐시가 비워지지 않음

            System.out.println("==============================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
