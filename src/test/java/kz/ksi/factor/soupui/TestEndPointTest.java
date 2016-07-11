package kz.ksi.factor.soupui;

import kz.factor.resources.test.TestRequest;
import kz.ksi.factor.test.soupui.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.server.MockWebServiceClient;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Erlan.Ibraev on 11.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebIntegrationTest(randomPort = true)
public class TestEndPointTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${local.server.port}")
    private int port = 0;

    private MockWebServiceClient mockClient;

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();


    @Before
    public void createClient() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(TestRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testEndPoint() {
        TestRequest request = new TestRequest();
        request.setTest("test");
        assertNotNull(new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:" + port + "/ws", request));
    }
}
