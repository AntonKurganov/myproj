package ua.company.shop.daos;

import ua.company.shop.domain.Good;
import ua.company.shop.domain.Line;

import java.util.List;

public interface LineDAO{
    void add(Line line);
    void delete(Line line);
    List<Line> list();
    List<Line> list(double minPrice, double maxPrice);
    List<Line> list(String country);
    List<Line> listByLength(int minLength, int maxLength);
    List<Line> listByWidth(double minWidth, double maxWidth);
    List<Line> listByStrength(double minBreakingStrength, double maxBreakingStrength);
    void persist(Good good);
}
