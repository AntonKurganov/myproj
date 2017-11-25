package ua.company.shop.domain;

import javax.persistence.*;

@Entity
public class Reel extends Good{

    @Enumerated(EnumType.STRING)
    @Column(name = "REEL_TYPE")
    private ReelType type;
    @Column(name = "BEARING_NUMBER")
    private int bearings;

    @Transient
    private String kind = "reel";

    public Reel(){}

    public Reel(Good g, ReelType type, int bearings){
        this.setName(g.getName());
        this.setPrice(g.getPrice());
        this.setCountry(g.getCountry());
        this.setPhotoLink(g.getPhotoLink());
        this.type = type;
        this.bearings = bearings;
    }

    public String toString(){
        return super.toString() + type + ", " + bearings + " bearings;";
    }

    public int getBearings() {
        return bearings;
    }

    public void setBearings(int bearings) {
        this.bearings = bearings;
    }

    public String getType() {
        char[] array = type.toString().toLowerCase().toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return String.valueOf(array);
    }

    public void setType(ReelType type) {
        this.type = type;
    }

    @Override
    public String getKind() {
        return kind;
    }

}
