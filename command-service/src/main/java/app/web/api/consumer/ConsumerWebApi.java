package app.web.api.consumer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/consumers")
public interface ConsumerWebApi {
    @PostMapping
    CompletableFuture<String> addConsumer(@RequestBody AddConsumerRequest addConsumerRequest);
}
