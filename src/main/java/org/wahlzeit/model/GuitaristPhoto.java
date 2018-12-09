package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class GuitaristPhoto extends Photo {
	private String guitaristName = "";
	private String guitar = "";

	public GuitaristPhoto() {
		super();
	}

	public GuitaristPhoto(Photo photo, String name, String guitar) {
		super(photo.id, photo.location);
		this.guitar = guitar;
		this.guitaristName = name;
	}

	public GuitaristPhoto(PhotoId myId, Location location, String name, String guitar){
		super(myId, location);
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
