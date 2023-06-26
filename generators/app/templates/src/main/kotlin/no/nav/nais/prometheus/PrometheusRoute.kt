package <%= appPackage %>.nais.prometheus

import io.ktor.http.ContentType
import io.ktor.server.application.call
import io.ktor.server.response.respondTextWriter
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.common.TextFormat

fun Routing.naisPrometheusRoute(
    collectorRegistry: CollectorRegistry = CollectorRegistry.defaultRegistry,
) {
    get("/internal/prometheus") {
        val names = call.request.queryParameters.getAll("name[]")?.toSet() ?: setOf()
        call.respondTextWriter(ContentType.parse(TextFormat.CONTENT_TYPE_004)) {
            TextFormat.write004(this, collectorRegistry.filteredMetricFamilySamples(names))
        }
    }
}
