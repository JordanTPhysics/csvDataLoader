package esg.csvloads.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ComponentScan("esg.csvloads.demo")
public class HttpRequestTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        System.out.println("Port number is: " + port);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/my-page",
                String.class)).contains("Upload a system csv file into the database");
    }
}
