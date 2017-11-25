package ua.company.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.shop.daos.GoodDAO;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.GoodImplementation;
import ua.company.shop.domain.GoodType;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GoodService extends AbstractService {

    @Autowired
    private GoodDAO goodDAO;

    @Transactional
    public void addGood(GoodImplementation good){
        goodDAO.add(good);
    }

    @Transactional
    public void deleteGood(GoodImplementation good){
        goodDAO.delete(good);
    }

    @Override
    public List<GoodImplementation> list(){
        return goodDAO.list();
    }

    @Override
    @Transactional
    public void persist(Good good) {
        goodDAO.persist(good);
    }

    @Transactional
    public List<GoodImplementation> list(GoodType goodType){
        return goodDAO.list(goodType);
    }

    @Transactional
    public List<GoodImplementation> list(double minPrice, double maxPrice, GoodType goodType){
        return goodDAO.list(minPrice, maxPrice, goodType);
    }

    @Transactional
    public List<GoodImplementation> list(String country, GoodType goodType){
        return goodDAO.list(country, goodType);
    }

}
