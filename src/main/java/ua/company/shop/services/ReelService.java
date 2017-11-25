package ua.company.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.shop.daos.ReelDAO;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Reel;
import ua.company.shop.domain.ReelType;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReelService extends AbstractService {

    @Autowired
    private ReelDAO reelDAO;

    @Transactional
    public void addReel(Reel reel){
        reelDAO.add(reel);
    }

    @Transactional
    public void deleteReel(Reel reel){
        reelDAO.delete(reel);
    }

    @Transactional
    public List list(double minPrice, double maxPrice){
        return reelDAO.list(minPrice, maxPrice);
    }

    @Transactional
    public List<Reel> list(String country){
        return reelDAO.list(country);
    }

    @Override
    public List<Reel> list(){
        return reelDAO.list();
    }

    @Transactional
    public List<Reel> listByType(String type){
        return reelDAO.listByType(ReelType.valueOf(type.toUpperCase()));
    }

    @Transactional
    public List<Reel> listByBN(int bearings){
        return reelDAO.listByBN(bearings);
    }

    @Override
    @Transactional
    public void persist(Good good){
        reelDAO.persist(good);
    }

}
