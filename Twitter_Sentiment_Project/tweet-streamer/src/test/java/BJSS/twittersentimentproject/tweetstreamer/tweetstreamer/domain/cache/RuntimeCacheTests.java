package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.cache;

import org.junit.Test;
import java.util.Optional;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RuntimeCacheTests {

    @Test
    public void canPutNewItemThenSuccessfullyRetrieveIt() {
        RuntimeCache cache = new RuntimeCache();
        Object testObject = new Object();

        cache.putItem("testItem", testObject);
        Optional<Object> result = cache.getItem("testItem", Object.class);

        assertThat("Object returned from cache was not what was stored",
                result.get(),
                is(equalTo(testObject)));
    }

    @Test
    public void canOverwriteItemThenSuccessfullyRetrieveIt() {
        RuntimeCache cache = new RuntimeCache();
        Object testObject = new Object();
        Object overwriteTestObject = new Object();

        cache.putItem("testing", testObject);
        cache.putItem("testing", overwriteTestObject);

        Optional<Object> result = cache.getItem("testing", Object.class);

        assertThat("Object returned from cache was not latest version",
                result.get(),
                is(equalTo(overwriteTestObject)));
    }

    @Test
    public void canGetItemCorrectlyWhenMultipleObjectsInStore() {
        RuntimeCache cache = new RuntimeCache();

        Object testObject = new Object();
        cache.putItem("key1", new Object());
        cache.putItem(1, testObject);
        cache.putItem("1", new Object());
        cache.putItem(new Object(), new Object());

        assertThat("Correct object returned from cache",
                cache.getItem(1, Object.class).get(),
                is(equalTo(testObject)));
    }

    @Test
    public void getItemThatDoesNotExistReturnsOptionalEmpty() {
        RuntimeCache cache = new RuntimeCache();
        Optional<String> result = cache.getItem("key", String.class);

        assertThat("Object was returned when should have been empty",
                result.isPresent(),
                is(equalTo(false)));
    }

    @Test
    public void getObjectItemWithWrongTypePassedReturnsOptionalEmpty() {
        RuntimeCache cache = new RuntimeCache();
        cache.putItem(1, "testingObject");

        Optional<Integer> result = cache.getItem(1, Integer.class);

        assertThat("Object was returned when wrong type was passed",
                result.isPresent(),
                is(equalTo(false)));
    }

    @Test
    public void canClearExistingKeySuccess() {
        RuntimeCache cache = new RuntimeCache();
        cache.putItem("testing", new Object());

        assertThat("Object stored correctly",
                cache.getItem("testing", Object.class).isPresent(),
                is(equalTo(true)));

        cache.clearKeyIfPresent("testing");

        assertThat("Object cleared correctly",
                cache.getItem("testing", Object.class).isPresent(),
                is(equalTo(false)));
    }

    @Test
    public void canClearNonExistingKeySuccess() {
        RuntimeCache cache = new RuntimeCache();
        cache.putItem("testing", new Object());

        cache.clearKeyIfPresent("notPresentKey");

        assertThat("Remaining object not effected by clear",
                cache.getItem("testing", Object.class).isPresent(),
                is(equalTo(true)));
    }
}