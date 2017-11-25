package ua.company.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.shop.daos.SpinningDAO;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Spinning;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpinningService extends AbstractService {

    @Autowired
    private SpinningDAO spinningDAO;

    @Transactional
    public void addSpinning(Spinning spinning){
        spinningDAO.add(spinning);
    }

    @Transactional
    public void deleteSpinning(Spinning spinning){
        spinningDAO.delete(spinning);
    }

    @Transactional
    public List<Spinning> list(double minPrice, double maxPrice){
        return spinningDAO.list(minPrice, maxPrice);
    }

    @Transactional
    public List<Spinning> list(String country){
        return spinningDAO.list(country);
    }

    @Override
    public List<Spinning> list(){
        return spinningDAO.list();
    }

    @Transactional
    public List<Spinning> listByLength(double minLength, double maxLength){
        return spinningDAO.listByLength(minLength, maxLength);
    }

    @Override
    @Transactional
    public void persist(Good good) {
        spinningDAO.persist(good);
    }
}
