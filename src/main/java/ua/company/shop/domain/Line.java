package ua.company.shop.domain;

import javax.persistence.*;

@Entity
public class Line extends Good{

    @Column(name = "LINE_LENGTH")
    private int lineLength;
    @Column(name = "BR_STRENGTH")
    private double brStrength;
    @Column(name = "LINE_WIDTH")
    private double width;

    @Transient
    private String kind = "line";

    public Line(){}

    public Line(Good g, int lineLength, double brStrength, double width){
        this.setName(g.getName());
        this.setPrice(g.getPrice());
        this.setCountry(g.getCountry());
        this.setPhotoLink(g.getPhotoLink());
        this.lineLength = lineLength;
        this.brStrength = brStrength;
        this.width = width;
    }

    public String toString(){
        return super.toString() + "Length: " + lineLength + "; Width: " + width + "; Breaking Strength: " + brStrength + ";";
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public double getBrStrength() {
        return brStrength;
    }

    public void setBrStrength(double brStrength) {
        this.brStrength = brStrength;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String getKind() {
        return kind;
    }
}
