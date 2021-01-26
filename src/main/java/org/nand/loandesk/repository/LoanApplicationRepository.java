package org.nand.loandesk.repository;

import org.nand.loandesk.entities.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {
    List<LoanApplication> findByCustomerId(Long customerId);
}
