package com.bosch.mongo.repository;

import com.bosch.mongo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CategoryRepository extends MongoRepository<Category,Integer> {

    Category findByCategoryId(int id);


    boolean existsByProductId(int productId);

    Category findByProductId(int id);
}
