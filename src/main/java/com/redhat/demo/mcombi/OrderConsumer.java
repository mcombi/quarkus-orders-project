package com.redhat.demo.mcombi;
import com.redhat.demo.mcombi.model.Order;
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class OrderConsumer {

    private final Logger logger = Logger.getLogger(OrderConsumer.class);

    @Incoming("orders-in")
    public void receive(Record<UUID, Order> record) {
        logger.infof("Got a order: %d - %s", record.key(), (Order)record.value());
    }
}