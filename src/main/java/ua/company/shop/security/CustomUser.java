package ua.company.shop.security;

import ua.company.shop.domain.Good;
import ua.company.shop.domain.GoodImplementation;
import ua.company.shop.domain.Spinning;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Users")
//@NamedQuery(name = "User.findByName", query = "SELECT u FROM Users u WHERE u.login = :login")
public class CustomUser {

    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;
    private String phoneNumber;
    private String email;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Good> goods = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public CustomUser(){}

    public CustomUser(String login, String password, UserRole role, String email, String phoneNumber){
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addGood(Good good) {
        if (!goods.contains(good))
            goods.add(good);
        if (!good.getUsers().contains(this))
            good.getUsers().add(this);
    }

    public void removeGood(Good good) {
        if (!goods.contains(good))
            goods.remove(good);
        if (!good.getUsers().contains(this))
            good.getUsers().remove(this);
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}
