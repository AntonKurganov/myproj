package ua.company.shop.daos;

import org.springframework.stereotype.Repository;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Reel;
import ua.company.shop.domain.ReelType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReelDAOImpl implements ReelDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Reel reel) {
        entityManager.merge(reel);
    }

    @Override
    public void delete(Reel reel) {
        entityManager.remove(reel);
    }

    @Override
    public List<Reel> list() {
        Query query = entityManager.createQuery("SELECT s FROM Reel s", Reel.class);
        return (List<Reel>) query.getResultList();
    }

    @Override
    public List<Reel> list(double minPrice, double maxPrice) {
        Query query = entityManager.createQuery("SELECT s FROM Reel s WHERE s.price BETWEEN :minPrice AND :maxPrice", Reel.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return (List<Reel>) query.getResultList();
    }

    @Override
    public List<Reel> list(String country) {
        Query query = entityManager.createQuery("SELECT s FROM Reel s WHERE s.country = :country", Reel.class);
        query.setParameter("country", country);
        return (List<Reel>) query.getResultList();
    }

    @Override
    public List<Reel> listByType(ReelType type) {
        Query query = entityManager.createQuery("SELECT s FROM Reel s WHERE s.type = :type", Reel.class);
        query.setParameter("type", type);
        return (List<Reel>) query.getResultList();
    }

    @Override
    public List<Reel> listByBN(int bearings) {
        Query query = entityManager.createQuery("SELECT s FROM Reel s WHERE s.bearings = :bearings", Reel.class);
        query.setParameter("bearings", bearings);
        return (List<Reel>) query.getResultList();
    }

    @Override
    public void persist(Good good) {
        entityManager.persist(good);
    }
}