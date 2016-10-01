package br.com.fexco.address.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fexco.address.model.Address;

public interface AddressRepository extends MongoRepository<Address, String> {

}
