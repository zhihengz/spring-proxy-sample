package sandbox;

public interface GenericCRUDService<T, K> {

    public void create( K k, T t );

    public T read( K k );

    public void update( K k, T t );

    public void delete( K k, T t );
    
}
