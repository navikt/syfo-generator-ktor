package <%= appPackage %>.metrics

import io.prometheus.client.Counter

const val NAMESPACE = "<%= appName %>"

val COUNT_MESSAGES: Counter =
    Counter.Builder()
        .namespace(NAMESPACE)
        .name("count_messages")
        .help("Counts the number of massages")
        .register()
