package <%= appPackage %>

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.routing
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import <%= appPackage %>.nais.isalive.naisIsAliveRoute
import <%= appPackage %>.nais.isready.naisIsReadyRoute
import <%= appPackage %>.nais.prometheus.naisPrometheusRoute
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ApplicationTest {

    @Test
    internal fun `Returns ok on is_alive`() {
        with(TestApplicationEngine()) {
            start()
            val applicationState = ApplicationState()
            applicationState.ready = true
            applicationState.alive = true
            application.routing { naisIsAliveRoute(applicationState) }

            with(handleRequest(HttpMethod.Get, "/internal/is_alive")) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("I'm alive! :)", response.content)
            }
        }
    }

    @Test
    internal fun `Returns ok in is_ready`() {
        with(TestApplicationEngine()) {
            start()
            val applicationState = ApplicationState()
            applicationState.ready = true
            applicationState.alive = true
            application.routing { naisIsReadyRoute(applicationState) }

            with(handleRequest(HttpMethod.Get, "/internal/is_ready")) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("I'm ready! :)", response.content)
            }
        }
    }

    @Test
    internal fun `Returns internal server error when liveness check fails`() {
        with(TestApplicationEngine()) {
            start()
            val applicationState = ApplicationState()
            applicationState.ready = false
            applicationState.alive = false
            application.routing {
                naisIsReadyRoute(applicationState)
                naisIsAliveRoute(applicationState)
                naisPrometheusRoute()
            }

            with(handleRequest(HttpMethod.Get, "/internal/is_alive")) {
                assertEquals(HttpStatusCode.InternalServerError, response.status())
                assertEquals("I'm dead x_x", response.content)
            }
        }
    }

    @Test
    internal fun `Returns internal server error when readyness check fails`() {
        with(TestApplicationEngine()) {
            start()
            val applicationState = ApplicationState()
            applicationState.ready = false
            applicationState.alive = false
            application.routing { naisIsReadyRoute(applicationState) }
            with(handleRequest(HttpMethod.Get, "/internal/is_ready")) {
                assertEquals(HttpStatusCode.InternalServerError, response.status())
                assertEquals("Please wait! I'm not ready :(", response.content)
            }
        }
    }
}
