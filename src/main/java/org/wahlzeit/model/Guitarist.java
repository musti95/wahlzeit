package org.wahlzeit.model;

public final class Guitarist {
    private  GuitaristType guitaristType;
    private  String guitaristName;
    private  String guitar;

    private Guitarist() {}

    public Guitarist(GuitaristType guitaristType, String guitaristName, String guitar) {
        this.guitaristType = guitaristType;
        this.guitaristName = guitaristName;
        this.guitar = guitar;
    }

    public GuitaristType getType() {
        return guitaristType;
    }

    public String getGuitaristName() {
        return guitaristName;
    }

    public String getGuitar() {
        return guitar;
    }
}
