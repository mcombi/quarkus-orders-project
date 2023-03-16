package com.redhat.demo.mcombi.resources;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.demo.mcombi.messaging.OrderProducer;
import com.redhat.demo.mcombi.model.Order;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.jboss.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name="orders")
public class OrderResource {


    @Inject
    OrderProducer producer;
    private final Logger logger = Logger.getLogger(OrderResource.class);
    @POST
    @Operation(summary = "Save an order")
    @APIResponse(
            responseCode = "200",
            description = "Gets all fights, or empty list if none",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Order.class, type = SchemaType.DEFAULT))
    )
    public Response send(Order order) {
        producer.sendOrderToKafka(order);
        // Return an 202 - Accepted response.
        logger.info("request received: " + order.toString());
        return Response.accepted().build();
    }

}

