package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GuitaristTypeTest {
    private GuitaristType base = new GuitaristType("base");
    private GuitaristType child1 = new GuitaristType("child1");
    private GuitaristType child2 = new GuitaristType("child2");

    @Test
    public void testSubtyping() {
        base.addSubType(child1);
        base.addSubType(child2);

        Set<GuitaristType> subTypes = new HashSet<>();
        Iterator<GuitaristType> subIter = base.getSubTypeIterator();
        while (subIter.hasNext()) {
            subTypes.add(subIter.next());
        }

        Assert.assertEquals(2, subTypes.size());
        Assert.assertTrue(subTypes.contains(child1));
        Assert.assertTrue(subTypes.contains(child2));

        Assert.assertEquals(base, child1.getSuperType());
        Assert.assertEquals(base, child2.getSuperType());

        Assert.assertFalse(base.isSubType());
        Assert.assertTrue(child1.isSubType());
        Assert.assertTrue(child2.isSubType());
    }
}
