package ua.company.shop.domain;

import javax.persistence.*;

@Entity
public class Spinning extends Good{

    @Column(name = "SPIN_LENGTH")
    private double length;
    @Column(name = "SPIN_WEIGHT")
    private int weight;

    @Transient
    private String kind = "spinning";

    public Spinning(){ }

    public Spinning(Good g, int weight, double length){
        this.setName(g.getName());
        this.setPrice(g.getPrice());
        this.setCountry(g.getCountry());
        this.setPhotoLink(g.getPhotoLink());
        this.weight = weight;
        this.length = length;
    }

    public String toString(){
        return super.toString() + "Length: " + length + "; Weight: " + weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String getKind() {
        return kind;
    }

}
