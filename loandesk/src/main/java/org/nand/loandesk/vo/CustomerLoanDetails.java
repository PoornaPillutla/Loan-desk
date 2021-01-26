package org.nand.loandesk.vo;

import org.nand.loandesk.entities.Customer;
import org.nand.loandesk.entities.LoanApplication;

import java.io.Serializable;
import java.util.List;

public class CustomerLoanDetails implements Serializable {
    private Customer customer;
    private List<LoanApplication> loans;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LoanApplication> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanApplication> loans) {
        this.loans = loans;
    }
}
