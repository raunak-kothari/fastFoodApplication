package com.web.userRepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.entity.OrderDetails;

@Repository
public interface ResturantRepo extends CrudRepository<OrderDetails, Long> {

}
