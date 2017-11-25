package ua.company.shop.daos;

import ua.company.shop.domain.Good;
import ua.company.shop.domain.Spinning;

import java.util.List;

public interface SpinningDAO{
    void add(Spinning spinning);
    void delete(Spinning spinning);
    List<Spinning> list();
    List<Spinning> list(double minPrice, double maxPrice);
    List<Spinning> list(String country);
    List<Spinning> listByLength(double minLength, double maxLength);
    void persist(Good good);
}
