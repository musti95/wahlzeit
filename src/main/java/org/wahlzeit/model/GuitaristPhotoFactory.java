package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class GuitaristPhotoFactory extends PhotoFactory {
	@Override
	public GuitaristPhoto createPhoto() {
		return new GuitaristPhoto();
	}

	@Override
	public GuitaristPhoto createPhoto(PhotoId id, Location location) {
		return new GuitaristPhoto(id, location, "", "");
	}

	public GuitaristPhoto createPhoto(PhotoId id, Location location, String name, String guitar) {
		return new GuitaristPhoto(id, location, name, guitar);
	}
}
