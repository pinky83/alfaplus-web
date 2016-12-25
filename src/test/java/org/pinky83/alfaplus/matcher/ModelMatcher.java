package org.pinky83.alfaplus.matcher;

import org.junit.Assert;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Дмитрий on 23.12.2016.//
 */
@SuppressWarnings("unchecked")
public class ModelMatcher<T> {
    private static final Comparator DEFAULT_COMPARATOR =
            (Object expected, Object actual) -> expected == actual || String.valueOf(expected).equals(String.valueOf(actual));

    private Comparator<T> comparator;

    public interface Comparator<T> {
        boolean compare(T expected, T actual);
    }

    public ModelMatcher() {
        this((Comparator<T>) DEFAULT_COMPARATOR);
    }

    public ModelMatcher(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private class Wrapper {
        private T entity;

        public Wrapper(T entity) {
            this.entity = entity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Wrapper that = (Wrapper) o;

            return entity != null ? comparator.compare(entity, that.entity) : that.entity == null;

        }

        @Override
        public String toString() {
            return String.valueOf(entity);
        }
    }

    public void assertEquals(T expected, T actual) {
        Assert.assertEquals(wrap(expected), wrap(actual));
    }

    public void assertNotEquals(T expected, T actual) {
        Assert.assertNotEquals(wrap(expected), wrap(actual));
    }

    public void assertCollectionEquals(Collection<T> expected, Collection<T> actual) {
        Assert.assertEquals(wrap(expected), wrap(actual));
    }

    public Wrapper wrap(T entity) {
        return new Wrapper(entity);
    }

    public List<Wrapper> wrap(Collection<T> collection) {
        return collection.stream().map(this::wrap).collect(Collectors.toList());
    }
}
