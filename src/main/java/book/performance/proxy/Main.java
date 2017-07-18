package book.performance.proxy;

import javassist.*;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class Main {

    private static final Class[] IDB_QUERY_INTERFACES = new Class[]{IDBQuery.class};

    public static void main(String[] args) throws Exception {
        System.out.println(new DBQueryProxy().request());
        System.out.println(createJDKQuery().request());
        System.out.println(createCglibProxy().request());
        System.out.println(createJavassistDynProxy().request());
        System.out.println(createJavassitBytecodeDynamicProxy().request());
    }

    public static IDBQuery createJDKQuery() {
        return (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                IDB_QUERY_INTERFACES,
                new JdkDbQueryHandler()
        );
    }

    public static IDBQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDbQueryInterceptor());
        enhancer.setInterfaces(IDB_QUERY_INTERFACES);
        return (IDBQuery) enhancer.create();
    }

    public static IDBQuery createJavassistDynProxy() throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(IDB_QUERY_INTERFACES);
        IDBQuery javassistProxy = (IDBQuery) proxyFactory.createClass().newInstance();
        ((ProxyObject) javassistProxy).setHandler(new JavassitDynDBQueryInterceptor());
        return javassistProxy;
    }

    public static IDBQuery createJavassitBytecodeDynamicProxy() throws Exception {
        ClassPool mPool = new ClassPool(true);
        CtClass mCtc = mPool.makeClass(IDBQuery.class.getName() + "Javaassist-BytecodeProxy");
        mCtc.addInterface(mPool.get(IDBQuery.class.getName()));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addField(CtField.make("public " + IDBQuery.class.getName() + " real;", mCtc));
        String dbQueryName = DBQuery.class.getName();
        mCtc.addMethod(CtNewMethod.make(
            "public String request() { " +
                "if (real == null) { " +
                    "real = new " +  dbQueryName + "(); " +
                "} " +
                "return real.request() + this.getClass().getCanonicalName(); " +
            "}",
                mCtc));

        return (IDBQuery) mCtc.toClass().newInstance();
    }
}
