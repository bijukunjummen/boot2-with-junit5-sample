package samples

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import java.nio.charset.StandardCharsets

@RunWith(SpringRunner::class)
@WebFluxTest(controllers = arrayOf(RouteConfig::class))
class SampleJunit4Test {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `get of hello uri should return Hello World!`() {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .consumeWith({ m ->
                    assertThat(String(m.responseBodyContent!!, StandardCharsets.UTF_8)).isEqualTo("Hello World!")
                })

    }

}