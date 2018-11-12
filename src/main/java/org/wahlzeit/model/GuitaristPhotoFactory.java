package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class GuitaristPhotoFactory extends PhotoFactory {
	@Override
	public GuitaristPhoto createPhoto() {
		return new GuitaristPhoto();
	}

	@Override
	public GuitaristPhoto createPhoto(PhotoId id) {
		return new GuitaristPhoto(id);
	}

	public GuitaristPhoto createPhoto(PhotoId id, String name, String guitar) {
		return new GuitaristPhoto(id, name, guitar);
	}
}
