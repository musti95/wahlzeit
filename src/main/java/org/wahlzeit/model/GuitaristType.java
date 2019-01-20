package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Ignore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class GuitaristType {
    @Ignore
    private GuitaristType superType = null;
    @Ignore
    private Set<GuitaristType> subTypes = new HashSet<>();
    private String name;

    private GuitaristType() {}

    public GuitaristType(String name) {
        this.name = name;
    }

    public Guitarist createInstance(String name, String guitar) {
        return new Guitarist(this, name, guitar);
    }

    public Iterator<GuitaristType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void addSubType(GuitaristType guitaristType) {
        assert (guitaristType != null);
        assert (guitaristType.superType == null);

        guitaristType.superType = this;
        subTypes.add(guitaristType);
    }

    public GuitaristType getSuperType() {
        return superType;
    }

    public boolean hasInstance(Guitarist guitarist) {
        assert (guitarist != null);
        if(guitarist.getType() == this) {
            return true;
        }

        for (GuitaristType type : subTypes) {
            if (type.hasInstance(guitarist)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSubType() {
        return superType != null;
    }

    public String getName() {
        return name;
    }
}
