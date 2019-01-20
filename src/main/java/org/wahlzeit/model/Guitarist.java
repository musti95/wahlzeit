package org.wahlzeit.model;

/**
 * Represents the Guitarist, that is visible on a GuitaristPhoto
 */
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

    /**
     * Get the type.
     * @return type of the guitarist
     */
	public GuitaristType getType() {
		return guitaristType;
	}

    /**
     * Get the guitarists name
     * @return name of the guitarist
     */
	public String getGuitaristName() {
		return guitaristName;
	}

    /**
     * Get the guitar.
     * @return guitar of the guitarist
     */
	public String getGuitar() {
		return guitar;
	}
}
