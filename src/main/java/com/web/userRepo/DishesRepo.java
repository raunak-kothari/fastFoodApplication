package com.web.userRepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.entity.Dishes;

@Repository
public interface DishesRepo extends CrudRepository<Dishes, Integer> {

}
