package ua.company.shop.services;

import ua.company.shop.domain.Good;

import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractService {

    @Transactional
    public abstract List<? extends Good> list();

    @Transactional
    public abstract void persist(Good good);
}
