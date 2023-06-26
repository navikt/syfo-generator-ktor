package <%= appPackage %>

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.prometheus.client.hotspot.DefaultExports

import <%= appPackage %>.application.ApplicationServer
import <%= appPackage %>.application.ApplicationState
import <%= appPackage %>.application.createApplicationEngine

val log: Logger = LoggerFactory.getLogger("<%= appPackage %>.<%= appName %>")
fun main() {
    val embeddedServer =
        embeddedServer(
            Netty,
            port = EnvironmentVariables().applicationPort,
            module = Application::module,
        )
    Runtime.getRuntime()
        .addShutdownHook(
            Thread {
                embeddedServer.stop(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(10))
            },
        )
    embeddedServer.start(true)
}

fun Application.module() {
    val environmentVariables = EnvironmentVariables()
    val applicationState = ApplicationState()

    environment.monitor.subscribe(ApplicationStopped) {
        applicationState.ready = false
        applicationState.alive = false
    }

    configureRouting(applicationState = applicationState)
}

data class ApplicationState(
    var alive: Boolean = true,
    var ready: Boolean = true,
)
