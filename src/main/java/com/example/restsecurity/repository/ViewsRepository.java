package com.example.restsecurity.repository;

import com.example.restsecurity.model.Views;
import org.springframework.data.repository.CrudRepository;

public interface ViewsRepository extends CrudRepository<Views, Integer> {
    long countByPostId(int userId);

}
