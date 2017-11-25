package ua.company.shop.domain;

public enum ReelType {
    INERTIAL, FIXEDSPOOL, MULTIPLYING;

    @Override
    public String toString() {
        return name();
    }
}
