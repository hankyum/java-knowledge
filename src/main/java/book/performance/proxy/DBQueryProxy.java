package book.performance.proxy;

public class DBQueryProxy implements IDBQuery {
    private IDBQuery real;

    @Override
    public String request() {
        if (real == null) {
            real = new DBQuery();
        }
        return real.request() + " " + this.getClass().getName();
    }
}
