package travelagency.service;

import travelagency.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface Service<T extends AbstractEntity> {
    Optional<T> findById(Long id);
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    T save(T entity);
    T update(T entity);
    void deleteById(Long id);
    boolean existsById(Long id);
}