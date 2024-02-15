package com.soni.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.soni.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
