package org.pinky83.alfaplus.matcher;

import org.junit.Assert;
import org.pinky83.alfaplus.TestUtil;
import org.pinky83.alfaplus.web.json.JsonUtil;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by Дмитрий on 23.12.2016.//
 */
@SuppressWarnings("unchecked")
public class ModelMatcher<T> {
    private static final Comparator DEFAULT_COMPARATOR =
            (Object expected, Object actual) -> expected == actual || String.valueOf(expected).equals(String.valueOf(actual));

    private Comparator<T> comparator;
    private Class<T> entityClass;

    public interface Comparator<T> {
        boolean compare(T expected, T actual);
    }

    public ModelMatcher(Class<T> entityClass) {
        this(entityClass, (Comparator<T>) DEFAULT_COMPARATOR);
    }

    public ModelMatcher(Class<T> entityClass, Comparator<T> comparator) {
        this.entityClass = entityClass;
        this.comparator = comparator;
    }

    private class Wrapper {
        private T entity;

        private Wrapper(T entity) {
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

    private T fromJsonValue(String json) {
        return JsonUtil.readValue(json, entityClass);
    }

    private Collection<T> fromJsonValues(String json) {
        return JsonUtil.readValues(json, entityClass);
    }

    public T fromJsonAction(ResultActions action) throws UnsupportedEncodingException {
        return fromJsonValue(TestUtil.getContent(action));
    }

    public void assertEquals(T expected, T actual) {
        Assert.assertEquals(wrap(expected), wrap(actual));
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

    public ResultMatcher contentMatcher(T expect) {
        return content().string(
                new TestMatcher<T>(expect) {
                    @Override
                    protected boolean compare(T expected, String body) {
                        Wrapper expectedForCompare = wrap(expected);
                        Wrapper actualForCompare = wrap(fromJsonValue(body));
                        return expectedForCompare.equals(actualForCompare);
                    }
                });
    }

    public final ResultMatcher contentListMatcher(T... expected) {
        return contentListMatcher(Arrays.asList(expected));
    }

    public final ResultMatcher contentListMatcher(List<T> expected) {
        return content().string(
                new TestMatcher<List<T>>(expected) {
                    @Override
                    protected boolean compare(List<T> expected, String actual) {
                        List<Wrapper> expectedList = wrap(expected);
                        List<Wrapper> actualList = wrap(fromJsonValues(actual));
                        return expectedList.equals(actualList);
                    }
                });
    }
}
