package studio.jfcoder.flake.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studio.jfcoder.flake.FlakeIdGen;

/**
 * sping boot config object.
 * Created by yejianfei on 2017/3/8.
 */
@Configuration()
@EnableConfigurationProperties(FlakeIdGenProperties.class)
public class FlakeIdGenConfig {

    @Autowired
    private FlakeIdGenProperties properties;

    @Bean
    public FlakeIdGen flakeIdGen() {
        return FlakeIdGen.instance(properties.getEpoch(), properties.getDatacenter(), properties.getWorker());
    }
}
