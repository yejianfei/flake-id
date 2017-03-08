package studio.jfcoder.flake.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * spring boot config properties.
 * Created by yejianfei on 2017/3/8.
 */
@ConfigurationProperties(prefix = "flake")
public class FlakeIdGenProperties {

    private long epoch = 1483228800000L;

    private int datacenter = 0;

    private int worker = 0;

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public int getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(int datacenter) {
        this.datacenter = datacenter;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }
}
