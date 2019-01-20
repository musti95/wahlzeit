package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.PatternInstance;

@Subclass(index=true)

@PatternInstance(
		patternName = "Abstract Factory",
		participants = {
				"ConcreteProduct"
		})
public class GuitaristPhoto extends Photo {
	private Guitarist guitarist;


	public GuitaristPhoto() {
		super();
		guitarist = null;
	}

	public GuitaristPhoto(Photo photo, String name, String guitar) {
		super(photo.id, photo.location);
		guitarist = GuitaristManager.getInstance().createGuitarist("solo", name, guitar);
	}

	public GuitaristPhoto(PhotoId myId, Location location, String name, String guitar) {
		super(myId, location);
		guitarist = GuitaristManager.getInstance().createGuitarist("solo", name, guitar);
	}

	public Guitarist getGuitarist() {
		return guitarist;
	}

	public void setGuitarist(Guitarist guitarist) {
		this.guitarist = guitarist;
	}
}
