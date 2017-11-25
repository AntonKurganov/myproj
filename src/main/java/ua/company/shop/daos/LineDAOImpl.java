package ua.company.shop.daos;

import org.springframework.stereotype.Repository;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Line;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LineDAOImpl implements LineDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(Line line) {
        entityManager.merge(line);
    }

    @Override
    public void delete(Line line) {
        entityManager.remove(line);
    }

    @Override
    public List<Line> list() {
        Query query = entityManager.createQuery("SELECT s FROM Line s", Line.class);
        return (List<Line>) query.getResultList();
    }

    @Override
    public List<Line> list(double minPrice, double maxPrice) {
        Query query = entityManager.createQuery("SELECT s FROM Line s WHERE s.price BETWEEN :minPrice AND :maxPrice", Line.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return (List<Line>) query.getResultList();
    }

    @Override
    public List<Line> list(String country) {
        Query query = entityManager.createQuery("SELECT s FROM Line s WHERE s.country = :country", Line.class);
        query.setParameter("country", country);
        return (List<Line>) query.getResultList();
    }

    @Override
    public List<Line> listByLength(int minLength, int maxLength) {
        Query query = entityManager.createQuery("SELECT s FROM Line s WHERE s.lineLength BETWEEN :minLength AND :maxLength", Line.class);
        query.setParameter("minLength", minLength);
        query.setParameter("maxLength", maxLength);
        return (List<Line>) query.getResultList();
    }

    @Override
    public List<Line> listByWidth(double minWidth, double maxWidth) {
        Query query = entityManager.createQuery("SELECT s FROM Line s WHERE s.width BETWEEN :minWidth AND :maxWidth", Line.class);
        query.setParameter("minWidth", minWidth);
        query.setParameter("maxWidth", maxWidth);
        return (List<Line>) query.getResultList();
    }

    @Override
    public List<Line> listByStrength(double minBreakingStrength, double maxBreakingStrength) {
        Query query = entityManager.createQuery("SELECT s FROM Line s WHERE s.brStrength BETWEEN :minBreakingStrength AND :maxBreakingStrength", Line.class);
        query.setParameter("minBreakingStrength", minBreakingStrength);
        query.setParameter("maxBreakingStrength", maxBreakingStrength);
        return (List<Line>) query.getResultList();
    }

    @Override
    public void persist(Good good) {
        entityManager.persist(good);
    }

}
