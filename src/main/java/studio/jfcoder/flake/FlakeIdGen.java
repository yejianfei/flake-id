package studio.jfcoder.flake;

/**
 * Generate the Flake ID is made up of: timestamp, datacenter, worker and counter.
 * Created by yejianfei on 2017/3/8.
 */
public class FlakeIdGen {

    private long last = 0L;

    private long epoch = 1483228800000L;

    private int datacenter = 0;

    private int worker = 0;

    private int counter = 0;

    private static FlakeIdGen gen;

    public static FlakeIdGen instance(int datacenter, int worker) {
        if (gen == null) {
            gen = new FlakeIdGen(datacenter, worker);
        }
        return gen;
    }

    public static FlakeIdGen instance(long epoch, int datacenter, int worker) {
        if (gen == null) {
            gen = new FlakeIdGen(datacenter, worker);
        }
        return gen;
    }

    public static FlakeIdGen instance() {
        if (gen == null) {
            gen = new FlakeIdGen();
        }
        return gen;
    }

    public static long gen() {
        return gen.next();
    }

    public FlakeIdGen() {

    }

    public FlakeIdGen(int datacenter, int worker) {
        this.datacenter = datacenter;
        this.worker = worker;
    }

    public FlakeIdGen(long epoch, int datacenter, int worker) {
        this.epoch = epoch;
        this.datacenter = datacenter;
        this.worker = worker;
    }

    public long getEpoch() {
        return epoch;
    }

    public int getDatacenter() {
        return datacenter;
    }

    public int getWorker() {
        return worker;
    }

    public synchronized long next() {
        try {
            // to fast gen the flake id.
            if (last == System.currentTimeMillis() && counter != 0 && counter % 4069 == 0) {
                Thread.sleep(1);
            }

            last = System.currentTimeMillis();
            return ((System.currentTimeMillis() - epoch) << 22) | (datacenter << 17) | (worker << 12) | (counter++ % 4069);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
