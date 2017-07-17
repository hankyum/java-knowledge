package book.performance.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkDbQueryHandler implements InvocationHandler {
    IDBQuery real;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (real == null) {
            real = new DBQuery();
        }
        return real.request() + " " + this.getClass().getCanonicalName();
    }
}
