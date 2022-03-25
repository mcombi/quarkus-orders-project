package com.redhat.demo.mcombi;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.demo.mcombi.model.Order;
import org.jboss.logging.Logger;


@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderProducer producer;
    private final Logger logger = Logger.getLogger(OrderResource.class);
    @POST
    public Response send(Order order) {
        producer.sendOrderToKafka(order);
        // Return an 202 - Accepted response.
        logger.info("request received: " + order.toString());
        return Response.accepted().build();
    }

}

