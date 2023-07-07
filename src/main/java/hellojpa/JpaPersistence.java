package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaPersistence {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // Transaction 시작
        // JPA의 모든 데이터 변경은 transaction 안에서 실행해야 함

        try {
            /* [영속성 컨텍스트]
            - 비영속 : 영속성 컨텍스트와 전혀 관계가 없는 상태
            Member member = new Member();

            - 영속 : 영속성 컨텍스트에 관리되는 상태
            em.persist(member); // 영속성 컨텍스트에 연결, 이 시점에 DB에 저장되는 것이 아님

            em.detach(member); // 영속성 컨텍스트에서 분리
            em.remove(member); // 영속성 컨텍스트에서 삭제, DB에서도 삭제됨
             */

            Member member1 = new Member(200L, "member200");
            Member member2 = new Member(300L, "member300");

            em.persist(member1);
            em.persist(member2);

            System.out.println("==============================");

            tx.commit(); // Transaction 커밋, 이 시점에 DB에 Query가 날라감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}