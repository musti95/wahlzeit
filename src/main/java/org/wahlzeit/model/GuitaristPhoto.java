package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class GuitaristPhoto extends Photo {
    private String guitaristName = "";
    private String guitar = "";

    public GuitaristPhoto() {
        super();
    }

    public GuitaristPhoto(PhotoId myId) {
        super(myId);
    }

    public GuitaristPhoto(PhotoId myId, String name, String guitar){
        super(myId);
        this.guitaristName = name;
        this.guitar = guitar;
    }

    public String getGuitaristName() {
        return guitaristName;
    }

    public void setGuitaristName(String guitaristName) {
        this.guitaristName = guitaristName;
    }

    public String getGuitar() {
        return guitar;
    }

    public void setGuitar(String guitar) {
        this.guitar = guitar;
    }
}
