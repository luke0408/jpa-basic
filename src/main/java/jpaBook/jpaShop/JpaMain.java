package jpaBook.jpaShop;

import jpaBook.jpaShop.domain.Order;
import jpaBook.jpaShop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // EntityManagerFactory에서 EntityManager를 생성

        EntityTransaction tx = em.getTransaction(); // Transaction 생성
        tx.begin(); // Transaction 시작

        try {
            // 비즈니스 로직
            Order order = new Order();
            order.addOrderItem(new OrderItem());

            tx.commit(); // Transaction 커밋
        } catch (Exception e) {
            tx.rollback(); // Transaction 롤백
        } finally {
            em.close(); // EntityManager 종료
        }

        emf.close(); // EntityManagerFactory 종료
    }
}
