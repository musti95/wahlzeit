package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;

/**
 * Manages Guitarists and their types.
 */
public class GuitaristManager extends ObjectManager {
	private static final GuitaristManager instance = new GuitaristManager();
	private HashMap<String, GuitaristType> types = new HashMap<>();

    /**
     * Get the instance of the GuitaristManager
     * @return instance of the GuitaristManager
     */
	public static GuitaristManager getInstance() {
		return instance;
	}

	private GuitaristManager() {
		addGuitaristType("solo");
		addGuitaristType("band");
	}

    /**
     * Create a new Guitarist.
     * @param typeName name of the type of the guitarist
     * @param guitaristName name of the guitarist
     * @param guitar name of the guitar
     * @return new Guitarist instance
     */
	public Guitarist createGuitarist(String typeName, String guitaristName, String guitar) {
		if (!types.containsKey(typeName)) {
			throw new IllegalArgumentException("GuitaristType '" + typeName + "' doesn't exist.");
		}
		return types.get(typeName).createInstance(guitaristName, guitar);
	}

    /**
     * Add a new GuitaristType to the application.
     * @param typeName name of the new type
     */
	public void addGuitaristType(String typeName) {
		assert(typeName != null);
		if (types.containsKey(typeName)) {
			throw new IllegalArgumentException("GuitaristType '" + typeName + "' already exists.");
		} else {
			types.put(typeName, new GuitaristType(typeName));
		}
	}
}
