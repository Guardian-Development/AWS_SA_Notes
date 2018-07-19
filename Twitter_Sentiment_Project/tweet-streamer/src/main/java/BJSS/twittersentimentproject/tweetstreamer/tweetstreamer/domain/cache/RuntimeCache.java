package BJSS.twittersentimentproject.tweetstreamer.tweetstreamer.domain.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RuntimeCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeCache.class);

    private Map<Object, Object> cachedItems = new HashMap<>();

    public <K, V> void putItem(K key, V item) {
        LOGGER.info("Putting key: " + key + " and Item: " + item + " into cache.");

        cachedItems.put(key, item);
    }

    public <K, V> Optional<V> getItem(K key, Class<V> itemType) {
        if(cachedItems.containsKey(key)) {
            Object item = cachedItems.get(key);
            if(itemType.isInstance(item)) {
                LOGGER.info("Cache returning option of some: " + item);

                return Optional.of(itemType.cast(item));
            }
        }

        LOGGER.info("Cache returning option of empty");
        return Optional.empty();
    }

    public <K> void clearKeyIfPresent(K key) {
        LOGGER.info("Clearing key: " + key);

        cachedItems.remove(key);
    }
}
