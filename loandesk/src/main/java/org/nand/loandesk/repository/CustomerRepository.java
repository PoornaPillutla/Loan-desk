package org.nand.loandesk.repository;

import org.nand.loandesk.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAll();
    Customer findCustomerByPhoneNumber(String phoneNum);
    Customer findCustomerByPhoneNumberAndPassword(String phoneNum, String password);
}
