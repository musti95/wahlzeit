package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;


@PatternInstance(
		patternName = "Abstract factory",
		participants = {
				"ConcreteFactory"
		})
public class GuitaristPhotoFactory extends PhotoFactory {
	@Override
	public GuitaristPhoto createPhoto() {
		return new GuitaristPhoto();
	}

	@Override
	public GuitaristPhoto createPhoto(PhotoId id, Location location) throws IllegalArgumentException {
		if(location == null) {
			throw new IllegalArgumentException("Location can't be null.");
		}
		return new GuitaristPhoto(id, location, "", "");
	}

	public GuitaristPhoto createPhoto(PhotoId id, Location location, String name, String guitar) {
		return new GuitaristPhoto(id, location, name, guitar);
	}
}
