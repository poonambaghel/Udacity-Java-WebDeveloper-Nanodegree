package com.udacity.PricingMicroServiceProj2.repository;

import com.udacity.PricingMicroServiceProj2.entity.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

}
