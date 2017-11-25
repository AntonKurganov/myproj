package ua.company.shop.security;

public enum UserRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return name();
    }
    
}
