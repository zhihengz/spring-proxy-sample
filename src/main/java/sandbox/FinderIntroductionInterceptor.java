package sandbox;

import org.springframework.aop.IntroductionInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class FinderIntroductionInterceptor implements IntroductionInterceptor {

    public Object invoke(MethodInvocation methodInvocation )
	throws Throwable {
	
	FinderExecutor executor = (FinderExecutor) methodInvocation.getThis();
	String methodName = methodInvocation.getMethod().getName();
	if ( methodName.startsWith( "find" ) ) {
	    Object[] args = methodInvocation.getArguments();
	    return executor.executeFinder( methodInvocation.getMethod(), args);
	} else {
	    return methodInvocation.proceed();
	}
    }

    public boolean implementsInterface( Class interfaze ) {
	return interfaze.isInterface() &&
	    FinderExecutor.class.isAssignableFrom( interfaze );
    }
}
