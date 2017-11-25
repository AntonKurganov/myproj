package ua.company.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.shop.daos.LineDAO;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Line;
import ua.company.shop.domain.Reel;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LineService extends AbstractService {

    @Autowired
    private LineDAO lineDAO;

    @Transactional
    public void addLine(Line line){
        lineDAO.add(line);
    }

    @Transactional
    public void deleteLine(Line line){
        lineDAO.delete(line);
    }

    @Transactional
    public List list(double minPrice, double maxPrice){
        return lineDAO.list(minPrice, maxPrice);
    }

    @Transactional
    public List<Line> list(String country){
        return lineDAO.list(country);
    }

    @Override
    @Transactional
    public List<Line> list(){
        return lineDAO.list();
    }

    @Transactional
    public List<Line> listByLength(int minLength, int maxLength){
        return lineDAO.listByLength(minLength, maxLength);
    }

    @Transactional
    public List<Line> listByWidth(double minWidth, double maxWidth){
        return lineDAO.listByWidth(minWidth, maxWidth);
    }

    @Transactional
    public List<Line> listByBrStrength(double minBrStrength, double maxBrStrength){
        return lineDAO.listByStrength(minBrStrength, maxBrStrength);
    }

    @Override
    @Transactional
    public void persist(Good good){
        lineDAO.persist(good);
    }

}
