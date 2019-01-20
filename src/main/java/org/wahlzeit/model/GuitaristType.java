package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Ignore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents the type of a Guitarist
 */
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

    /**
     * Create a new Guitarist instance of this type.
     * @param name of the guitarist
     * @param guitar of the guitarist
     * @return new Guitarist instance of this type
     */
	public Guitarist createInstance(String name, String guitar) {
		return new Guitarist(this, name, guitar);
	}

    /**
     * Get an iterator to this types subtypes
     * @return iterator to subtypes
     */
	public Iterator<GuitaristType> getSubTypeIterator() {
		return subTypes.iterator();
	}

    /**
     * Add a subtype to this type.
     * @param guitaristType subtype to be added
     */
	public void addSubType(GuitaristType guitaristType) {
		assert (guitaristType != null);
		assert (guitaristType.superType == null);

		guitaristType.superType = this;
		subTypes.add(guitaristType);
	}

    /**
     * Get Supertype of this GuitaristType
     * @return supertype of this type or null if none set
     */
	public GuitaristType getSuperType() {
		return superType;
	}

    /**
     * Check if a Guitarist has this type or one of it's subtypes
     * @param guitarist guitarist to be checked
     * @return true is guitarist is of this type or one of it's subtypes
     */
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

    /**
     * Check if this type is a subtype
     * @return true if this type has a supertype
     */
	public boolean isSubType() {
		return superType != null;
	}

    /**
     * Get the name of this GuitaristType
     * @return name of this type
     */
	public String getName() {
		return name;
	}
}
