package studio.jfcoder.flake;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * test case of the FlakeIdGen class.
 * Created by yejianfei on 2017/3/8.
 */
public class FlakeIdGenTest {

    private static final long EPOCH = System.currentTimeMillis();

    private static final int DATA_CENTER =  1;

    private static final int WORKER = 1;

    @Test
    public void testNext() throws Exception {

        final FlakeIdGen flake = new FlakeIdGen(EPOCH, DATA_CENTER, WORKER);
        final Set<Long> ids = new HashSet<Long>();
        final List<Long> tasks = new ArrayList<Long>();
        final Random random = new Random();
        int count = 8192;


        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        long id = flake.next();
                        Thread.sleep(random.nextInt(100));
                        synchronized (tasks) {
                            ids.add(id);
                            tasks.add(Thread.currentThread().getId());
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        while(tasks.size() < count) {
            Thread.sleep(1000);
        }

        assertEquals(ids.size(), count);
    }

    @Test
    public void testSpringBoot() throws Exception {

    }

}
