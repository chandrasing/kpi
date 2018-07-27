package app.resource.rest;

import app.resource.entity.ResourceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/resources")
public interface ResourceRestApi {
    @GetMapping
    CompletableFuture<ResponseEntity<Page<ResourceEntity>>> findAll(Pageable pageable);

    @GetMapping(path = "{id}")
    CompletableFuture<ResponseEntity<ResourceEntity>> findById(@PathVariable("id") String id);
}
