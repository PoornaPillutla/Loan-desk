package org.nand.loandesk.dao;

import org.nand.loandesk.entities.Customer;
import org.nand.loandesk.entities.LoanApplication;
import org.nand.loandesk.repository.CustomerRepository;
import org.nand.loandesk.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanDeskDao {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public Customer createCustomer(Customer customer){
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    public Customer findCustomer(String phoneNum){
        return customerRepository.findCustomerByPhoneNumber(phoneNum);
    }

    public Customer validateLogin(String phoneNum,String password){
        return customerRepository.findCustomerByPhoneNumberAndPassword(phoneNum,password);
    }

    public LoanApplication createLoanApplication(LoanApplication loanApplication ){
        return loanApplicationRepository.save(loanApplication);
    }

    public List<LoanApplication> getLoanApplicationsByCustomer(Long customerId){
        return loanApplicationRepository.findByCustomerId(customerId);
    }

    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
