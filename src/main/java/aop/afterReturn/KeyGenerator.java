package aop.afterReturn;

import java.util.Random;

public class KeyGenerator {

    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF09F590AE56L;

    private Random random = new Random();

    public long getKey() {
        int x = random.nextInt(3);
        if(x == 1) {
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
