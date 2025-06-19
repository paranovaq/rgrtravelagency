package travelagency.controller;

import travelagency.entity.AbstractEntity;
import travelagency.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

public abstract class AbstractController<T extends AbstractEntity> {
    protected HttpHeaders headers;

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = getService().findAll();
        return entities.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(entities, headers, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<T>> getAllPaginated(Pageable pageable) {
        Page<T> page = getService().findAll(pageable);
        return page.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        Optional<T> entity = getService().findById(id);
        return entity.map(value -> new ResponseEntity<>(value, headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T savedEntity = getService().save(entity);
        return new ResponseEntity<>(savedEntity, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T entity) {
        if (!getService().existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entity.setId(id);
        T updatedEntity = getService().update(entity);
        return new ResponseEntity<>(updatedEntity, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!getService().existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        getService().deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public abstract Service<T> getService();
}