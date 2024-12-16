package org.acme.rabbitmq.processor

import io.smallrye.reactive.messaging.annotations.Blocking
import jakarta.enterprise.context.ApplicationScoped
import org.acme.rabbitmq.model.Quote
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import java.util.*

/**
 * A bean consuming data from the "quote-requests" RabbitMQ queue and giving out a random quote.
 * The result is pushed to the "quotes" RabbitMQ exchange.
 */
@ApplicationScoped
class QuoteProcessor {
    private val random =
        Random()

    @Incoming(
        "requests"
    )
    @Outgoing(
        "quotes"
    )
    @Blocking
    @Throws(
        InterruptedException::class
    )
    fun process(
        quoteRequest: String?
    ): Quote {
        // simulate some hard-working task
        Thread.sleep(
            1000
        )
        return Quote(
            quoteRequest,
            random.nextInt(
                100
            )
        )
    }
}