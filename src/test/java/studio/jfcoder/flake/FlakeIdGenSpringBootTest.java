package studio.jfcoder.flake;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studio.jfcoder.flake.spring.FlakeIdGenConfig;
import studio.jfcoder.flake.spring.FlakeIdGenProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * test case of spring runtime
 * Created by yejianfei on 2017/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlakeIdGenConfig.class)
public class FlakeIdGenSpringBootTest {

    @Autowired
    FlakeIdGen gen;

    @Autowired
    FlakeIdGenProperties properties;

    @Test
    public void testConfig() throws Exception {
        assertEquals(gen, FlakeIdGen.instance());
        assertEquals(gen.getEpoch(), properties.getEpoch());
        assertEquals(gen.getDatacenter(), properties.getDatacenter());
        assertEquals(gen.getWorker(), properties.getWorker());
        assertNotEquals(FlakeIdGen.gen(), 0L);
    }
}
