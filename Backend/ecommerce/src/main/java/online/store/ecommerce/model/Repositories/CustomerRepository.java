package online.store.ecommerce.model.Repositories;

import org.springframework.data.repository.CrudRepository;

import online.store.ecommerce.model.Entites.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByCustomerid(Long customerid);
}