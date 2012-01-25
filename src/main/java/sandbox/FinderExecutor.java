package sandbox;

import java.util.List;
import java.lang.reflect.Method;

public interface FinderExecutor<T> {

    public List<T> executeFinder(Method method, final Object[] queryArgs );
}
