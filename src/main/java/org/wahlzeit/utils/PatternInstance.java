package org.wahlzeit.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Repeatable(PatternInstances.class)
public @interface PatternInstance {
    String patternName();
    String[] participants();
}