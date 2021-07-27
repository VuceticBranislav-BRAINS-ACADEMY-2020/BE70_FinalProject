package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
	
	@Override
	@Query(value = "FROM AddressEntity AS a WHERE a.id = :id AND a.status <> 'DELETED'")
	Optional<AddressEntity> findById(@Param("id") Integer id);
	 
	@Override
	Set<AddressEntity> findAll();
	
	@Query(value = "SELECT COUNT(*)>0 FROM AddressEntity AS a WHERE a.city = :city AND a.street = :street AND a.number = :number AND a.apartment = :apartment")
	Boolean isAddressExists(@Param("city") String city, @Param("street") String street, @Param("number") String number, @Param("apartment") String apartment);
	
	@Query(value = "SELECT COUNT(*)>0 FROM AddressEntity AS a WHERE a.city = :city AND a.street = :street AND a.number = :number")
	Boolean isAddressExists(@Param("city") String city, @Param("street") String street, @Param("number") String number);
	
	@Query(value = "FROM AddressEntity AS a WHERE a.status <> 'DELETED'")
	List<AddressEntity> findAllUndeleted();

}
