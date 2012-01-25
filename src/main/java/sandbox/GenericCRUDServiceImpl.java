package sandbox;

import java.util.HashMap;
import java.util.Map;

public class GenericCRUDServiceImpl<T, K> implements GenericCRUDService<T, K> {

    private Map<K, T> store = new HashMap<K, T>();

    public void create( K k, T t ) {
        store.put( k, t );
    }

    public T read( K k ) {
        return store.get( k );
    }

    public void update( K k, T t ) {
        store.put( k, t );
    }

    public void delete( K k, T t ) {
        store.remove( k );
    }
    
}
