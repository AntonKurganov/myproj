package ua.company.shop.domain;

import ua.company.shop.security.CustomUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Goods")
public class Good {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "PHOTO_LINK")
    private String photoLink;

    @Transient
    private String kind = "good";

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = {@JoinColumn(name = "good_Id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_Id", referencedColumnName = "id")})
    private List<CustomUser> users = new ArrayList<>();

    public Good(){}

    public Good(String name, double price, String country, String photoLink){
        this.name = name;
        this.price = price;
        this.country = country;
        this.photoLink = photoLink;
    }

    public void addUser(CustomUser user) {
        if (!users.contains(user))
            users.add(user);
        if (!user.getGoods().contains(this))
            user.getGoods().add(this);
    }

    public void removeUser(CustomUser user) {
        if (users.contains(user))
            users.remove(user);
        if (user.getGoods().contains(this))
            user.getGoods().remove(this);
    }

    public String toString(){
        return name + ": " + price + "hrn; Country: " + country + "\n";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public List<CustomUser> getUsers() {
        return users;
    }

    public void setUsers(List<CustomUser> users) {
        this.users = users;
    }

    public String getKind() {
        return kind;
    }
}
