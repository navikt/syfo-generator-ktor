package <%= appPackage %>

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.prometheus.client.hotspot.DefaultExports

import <%= appPackage %>.application.ApplicationServer
import <%= appPackage %>.application.ApplicationState
import <%= appPackage %>.application.createApplicationEngine

val log: Logger = LoggerFactory.getLogger("<%= appPackage %>.<%= appName %>")

fun main() {
    val env = Environment()
    DefaultExports.initialize()
    val applicationState = ApplicationState()
    val applicationEngine = createApplicationEngine(
            env,
            applicationState
    )
    val applicationServer = ApplicationServer(applicationEngine, applicationState)
    applicationServer.start()
}