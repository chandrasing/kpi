package app.web.api.resource.impl;

import app.resource.commands.ResourceCommandFactory;
import app.web.api.resource.AddResourceRequest;
import app.web.api.resource.ResourceWebApi;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ResourceWebApiImpl implements ResourceWebApi {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CommandGateway commandGateway;
    private final ResourceCommandFactory commandFactory = ResourceCommandFactory.INSTANCE.INSTANCE;

    public ResourceWebApiImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> addConsumer(AddResourceRequest newResource) {
        String id = UUID.randomUUID().toString();
        Assert.hasLength(newResource.getName(), "Missing resource name");
        Assert.hasLength(newResource.getType(), "Missing resource type");
        Assert.hasLength(newResource.getUnit(), "Missing resource unit");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                commandGateway.sendAndWait(commandFactory.newAddResourceCommand(id, newResource.getName(),newResource.getType(),newResource.getUnit()))
        );

        return completableFuture
                .thenApply(s -> s)
                .exceptionally((e) -> {
                    logger.warn("Add consumer {} failed", newResource);
                    throw new RuntimeException(String.format("Add consumer %s failed because of %s ", newResource, e.getMessage()), e);
                });
    }
}
