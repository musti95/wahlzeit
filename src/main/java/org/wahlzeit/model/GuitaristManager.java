package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;

public class GuitaristManager extends ObjectManager {
    private static final GuitaristManager instance = new GuitaristManager();
    private HashMap<String, GuitaristType> types = new HashMap<>();

    public static GuitaristManager getInstance() {
        return instance;
    }

    public GuitaristManager() {
        addGuitaristType("solo");
        addGuitaristType("band");
    }

    public Guitarist createGuitarist(String typeName, String guitaristName, String guitar) {
        if (!types.containsKey(typeName)) {
            throw new IllegalArgumentException("GuitaristType '" + typeName + "' doesn't exist.");
        }
        return types.get(typeName).createInstance(guitaristName, guitar);
    }

    public void addGuitaristType(String typeName) {
        assert(typeName != null);
        if (types.containsKey(typeName)) {
            throw new IllegalArgumentException("GuitaristType '" + typeName + "' already exists.");
        } else {
            types.put(typeName, new GuitaristType(typeName));
        }
    }
}
