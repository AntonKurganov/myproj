package ua.company.shop.daos;

import ua.company.shop.domain.Good;
import ua.company.shop.domain.Reel;
import ua.company.shop.domain.ReelType;

import java.util.List;

public interface ReelDAO {
    void add(Reel reel);
    void delete(Reel reel);
    List<Reel> list();
    List<Reel> list(double minPrice, double maxPrice);
    List<Reel> list(String country);
    List<Reel> listByType(ReelType type);
    List<Reel> listByBN(int bearings);
    void persist(Good good);
}
