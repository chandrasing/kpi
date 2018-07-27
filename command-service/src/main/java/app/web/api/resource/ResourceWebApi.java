package app.web.api.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/resources")
public interface ResourceWebApi {
    @PostMapping
    CompletableFuture<String> addConsumer(@RequestBody AddResourceRequest addConsumerRequest);
}
