package ua.company.shop.daos;

import org.springframework.stereotype.Repository;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Spinning;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SpinningDAOImpl implements SpinningDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Spinning spinning) {
        entityManager.merge(spinning);
    }

    @Override
    public void delete(Spinning spinning) {
        entityManager.remove(spinning);
    }

    @Override
    public List<Spinning> list() {
        Query query = entityManager.createQuery("SELECT s FROM Spinning s", Spinning.class);
        return (List<Spinning>) query.getResultList();
    }

    @Override
    public List<Spinning> list(double minPrice, double maxPrice) {
        Query query = entityManager.createQuery("SELECT s FROM Spinning s WHERE s.price BETWEEN :minPrice AND :maxPrice", Spinning.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return (List<Spinning>) query.getResultList();
    }

    @Override
    public List<Spinning> list(String country) {
        Query query = entityManager.createQuery("SELECT s FROM Spinning s WHERE s.country = :country", Spinning.class);
        query.setParameter("country", country);
        return (List<Spinning>) query.getResultList();
    }

    @Override
    public List<Spinning> listByLength(double minLength, double maxLength) {
        Query query = entityManager.createQuery("SELECT s FROM Spinning s WHERE s.length BETWEEN :minLength AND :maxLength", Spinning.class);
        query.setParameter("minLength", minLength);
        query.setParameter("maxLength", maxLength);
        return (List<Spinning>) query.getResultList();
    }

    @Override
    public void persist(Good good) {
        entityManager.persist(good);
    }

}
