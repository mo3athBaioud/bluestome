package info.magnolia.cms.cache;

import info.magnolia.cms.core.Content;

import javax.jcr.RepositoryException;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;


/**
 * @author Andreas Brenk
 * @author Fabrizio Giustina
 * @since 3.0
 */
class CacheConfigListener implements EventListener {

    private CacheManager cacheManager;

    private Content content;

    protected CacheConfigListener(CacheManager cacheManager, Content content) {
        this.cacheManager = cacheManager;
        this.content = content;
    }

    public void onEvent(EventIterator events) {
        try {
            this.content.refresh(false);
        }
        catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        // this.cacheManager.setConfigNode(this.content); // FIXME
        this.cacheManager.restart();
    }
}
