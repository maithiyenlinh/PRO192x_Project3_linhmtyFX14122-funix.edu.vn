import java.util.concurrent.atomic.AtomicInteger;
public class IdGenerator {
    private String prefix;
    private AtomicInteger lastId;
    public IdGenerator() {
        this.lastId = new AtomicInteger();
        this.prefix = "";
    }
    public void init(String prefix, int lastId) {
        this.prefix = prefix;
        this.lastId.set(lastId);
    }
    public String generate() {
        return this.prefix + String.format("%03d", this.lastId.incrementAndGet());
    }
    public String generate(int coef) {
        return this.prefix + String.format("%04d", this.lastId.incrementAndGet() * coef);
    }
    public int getId() {
        return this.lastId.get();
    }
}