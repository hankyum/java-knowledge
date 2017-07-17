package book.performance.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        System.out.println(new DBQueryProxy().request());
        System.out.println(createJDKQuery().request());
        System.out.println(createCglibProxy().request());
    }

    public static IDBQuery createJDKQuery() {
        return (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[] {IDBQuery.class},
                new JdkDbQueryHandler()
        );
    }

    public static IDBQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDbQueryInterceptor());
        enhancer.setInterfaces(new Class[] {IDBQuery.class});
        return (IDBQuery) enhancer.create();
    }
}
