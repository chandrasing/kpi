package app.web.api.consumer.impl;

import app.consumer.commands.ConsumerCommandFactory;
import app.web.api.consumer.AddConsumerRequest;
import app.web.api.consumer.ConsumerWebApi;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ConsumerWebApiImpl implements ConsumerWebApi {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CommandGateway commandGateway;
    private final ConsumerCommandFactory commandFactory = ConsumerCommandFactory.INSTANCE;

    public ConsumerWebApiImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> addConsumer(AddConsumerRequest newConsumer) {
        String consumerId = UUID.randomUUID().toString();
        Assert.hasLength(newConsumer.getName(), "Missing consumer name");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                commandGateway.sendAndWait(commandFactory.newAddConsumerCommand(consumerId, newConsumer.getName()))
        );
        return completableFuture
                .thenApply(s -> s)
                .exceptionally((e) -> {
                    logger.warn("Add consumer {} failed", newConsumer);
                    throw new RuntimeException(String.format("Add consumer %s failed because of %s ", newConsumer, e.getMessage()), e);
                });
    }
}
