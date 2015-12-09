package br.com.drivesales.test;

import br.com.drivesales.test.ApplicationTest;
import org.slf4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Thomas Daniel Kaneko Teixeira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:applicationtest.properties")
@SpringApplicationConfiguration(classes = {ApplicationTest.class})
@WebIntegrationTest({"server.port=0", "management.port=0"})
public abstract class BaseTest {
    
    protected Logger logger;
    @Value("${local.server.port}")
    protected int port;
    protected final static String URL = "http://localhost:";
    protected final static String SCRIPT_RESOURCE = "/script";

    public void setPort(int port) {
        this.port = port;
    }
}
