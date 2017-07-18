package book.performance.proxy;

import javassist.util.proxy.MethodHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class JavassitDynDBQueryInterceptor implements MethodHandler {
    IDBQuery real;

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
        if (real == null) {
            real = new DBQuery();
        }
        return real.request() + " " + this.getClass().getCanonicalName();    }
}
