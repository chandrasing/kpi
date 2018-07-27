package app.consumer.rest;

import app.consumer.entity.ConsumerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/consumers")
public interface ConsumerRestApi {
    @GetMapping
    CompletableFuture<ResponseEntity<Page<ConsumerEntity>>> findAll(Pageable pageable);

    @GetMapping(path = "{id}")
    CompletableFuture<ResponseEntity<ConsumerEntity>> findById(@PathVariable("id") String id);
}
