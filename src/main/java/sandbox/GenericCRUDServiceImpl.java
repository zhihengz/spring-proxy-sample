package sandbox;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.beans.Introspector;

public class GenericCRUDServiceImpl<T, K> 
    implements GenericCRUDService<T, K>, FinderExecutor<T> {

    private Map<K, T> store = new HashMap<K, T>();
    
    private Class<T> clazz;

    private Map<String, PropertyDescriptor> propertyDescriptorMap =  
        new HashMap<String, PropertyDescriptor>();

    public GenericCRUDServiceImpl( Class<T> clazz ) {
        this.clazz = clazz;
    }

    private void init() throws Exception {

        PropertyDescriptor[] descriptors = Introspector.getBeanInfo( clazz ).getPropertyDescriptors();
        for ( PropertyDescriptor descriptor : descriptors ) {
            propertyDescriptorMap.put( descriptor.getName(), descriptor );
        }
    }

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
    
    public List<T> executeFinder(Method method, final Object[] queryArgs ) {
        String propertyName = getQueryPropertyName( method );
        PropertyDescriptor descriptor = propertyDescriptorMap.get( propertyName );
        if ( descriptor == null ) {
            throw new RuntimeException( "no property resolved to " + propertyName + " in class " + clazz.getName() );
        }
        Method getter = descriptor.getReadMethod();
        List<T> resultList =  new ArrayList<T>();
        for ( T t : store.values() ) {
            try {
                Object value = getter.invoke( t );
                if ( value != null && value.equals( queryArgs[0] ) ) {
                    resultList.add( t );
                }
            } catch ( Exception e ) {
                throw new RuntimeException( "caught exception during invoke getter on " + propertyName );
            }
        }
        return resultList;
    }

    private String getQueryPropertyName( Method finderMethod ) {
        String methodName = finderMethod.getName();
        if ( methodName.startsWith( "findBy" ) ) {
            String propertyName = Introspector.decapitalize( methodName.substring( "findBy".length(), methodName.length() ) );
            System.out.println( propertyName );
            return propertyName;
        } else {
            throw new RuntimeException( methodName + " cannot be resolved" );
        }
    }
}
