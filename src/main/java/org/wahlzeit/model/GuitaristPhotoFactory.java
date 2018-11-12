package org.wahlzeit.model;

public class GuitaristPhotoFactory extends PhotoFactory {
    @Override
    public Photo createPhoto() {
        return new GuitaristPhoto();
    }

    @Override
    public Photo createPhoto(PhotoId id) {
        return new GuitaristPhoto(id);
    }

    public Photo createPhoto(PhotoId id, String name, String guitar) {
        return new GuitaristPhoto(id, name, guitar);
    }
}
