package ua.company.shop.domain;

import javax.persistence.*;

@Entity
public class GoodImplementation extends Good{

    @Enumerated(EnumType.STRING)
    @Column(name = "GOOD_TYPE")
    private GoodType type;

//    @Transient
//    private String kind = "good";

    public GoodImplementation(){}

    public GoodImplementation(Good g, GoodType type) {
        this.setName(g.getName());
        this.setPrice(g.getPrice());
        this.setCountry(g.getCountry());
        this.setPhotoLink(g.getPhotoLink());
        this.type = type;
    }

    public String toString(){
        return super.toString() + " " + type + ";";
    }

    public GoodType getType() {
        return type;
    }

    public void setType(GoodType type) {
        this.type = type;
    }
//
//    public String getKind(){
//        return kind;
//    }

}
