package ua.company.shop.daos;

import org.springframework.stereotype.Repository;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.GoodImplementation;
import ua.company.shop.domain.GoodType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GoodDAOImpl implements GoodDAO{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void add(GoodImplementation good) {
        entityManager.merge(good);
    }

    @Override
    public void delete(GoodImplementation good) {
        entityManager.remove(good);
    }

    @Override
    public List<GoodImplementation> list() {
        Query query = entityManager.createQuery("SELECT s FROM GoodImplementation s", GoodImplementation.class);
        return (List<GoodImplementation>) query.getResultList();
    }

    @Override
    public List<GoodImplementation> list(GoodType goodType) {
        Query query = entityManager.createQuery("SELECT s FROM GoodImplementation s WHERE s.type = :type", GoodImplementation.class);
        query.setParameter("type", goodType);
        return (List<GoodImplementation>) query.getResultList();
    }

    @Override
    public List<GoodImplementation> list(String country, GoodType goodType) {
        Query query = entityManager.createQuery("SELECT s FROM GoodImplementation s WHERE s.country = :country AND s.type = :type", GoodImplementation.class);
        query.setParameter("country", country);
        query.setParameter("type", goodType);
        return (List<GoodImplementation>) query.getResultList();
    }

    @Override
    public void persist(Good good) {
        entityManager.persist(good);
    }

    @Override
    public List<GoodImplementation> list(double minPrice, double maxPrice, GoodType goodType) {
        Query query = entityManager.createQuery("SELECT s FROM GoodImplementation s WHERE s.price BETWEEN :minPrice AND :maxPrice AND s.type = :type", GoodImplementation.class);
        query.setParameter("type", goodType);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return (List<GoodImplementation>) query.getResultList();
    }

}
