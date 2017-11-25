package ua.company.shop.daos;

import ua.company.shop.domain.Good;
import ua.company.shop.domain.GoodImplementation;
import ua.company.shop.domain.GoodType;
import java.util.List;

public interface GoodDAO {
    void add(GoodImplementation anotherGood);
    void delete(GoodImplementation anotherGood);
    List<GoodImplementation> list();
    List<GoodImplementation> list(GoodType goodType);
    List<GoodImplementation> list(double minPrice, double maxPrice, GoodType goodType);
    List<GoodImplementation> list(String country, GoodType goodType);
    void persist(Good good);
}
