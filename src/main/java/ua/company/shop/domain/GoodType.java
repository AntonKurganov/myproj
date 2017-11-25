package ua.company.shop.domain;

public enum GoodType {
    FLOAT, HOOK, HOOKLINK, WEIGHT, FEEDER,
    WOBBLER, SPOON, TWISTER, JIGHEAD,
    BAIT,
    HOLDALL, BELL, STAND, DAGGER, KEEPNET, CLONK;

    @Override
    public String toString() {
        return name();
    }

    public String parseType(){
        char[] array = name().toLowerCase().toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return String.valueOf(array);
    }

    public static boolean exists(String goodType){
        try {
            GoodType.valueOf(goodType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

}
