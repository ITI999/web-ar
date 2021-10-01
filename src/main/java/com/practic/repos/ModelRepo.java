package com.practic.repos;

import com.practic.entity.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepo extends CrudRepository<Model, Long> {
    List<Model> findBySet_id(Long set_id);
}