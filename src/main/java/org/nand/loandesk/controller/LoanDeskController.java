package org.nand.loandesk.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nand.loandesk.entities.Customer;
import org.nand.loandesk.entities.LoanApplication;
import org.nand.loandesk.service.LoanDeskService;
import org.nand.loandesk.vo.CustomerLoanDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("loandesk")
public class LoanDeskController {

    @Autowired
    private LoanDeskService loanDeskService;

    @GetMapping("/health")
    public String health(){
        return "Hi,I am loan desk app.i am doing good.";
    }

    @PostMapping("/loan")
    public ResponseEntity createLoan(@RequestBody String req){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode loanNode = objectMapper.readTree(req);

            String phoneNum = loanNode.get("phoneNumber").asText();
            Customer customer = loanDeskService.findCustomer(phoneNum);
            if(customer!=null){
                Long loanId = loanDeskService.createLoanApplication(loanNode,customer);
                //String fileName = "Loan_"+new Date()+".json";
                //objectMapper.writeValue(new File("/home/nandeesh/Desktop/Loan_Desk/"+fileName),loanNode);

                return ResponseEntity.ok(loanId);

            }else{
                return ResponseEntity.badRequest()
                        .body("Customer details not found with phone number "+phoneNum);
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/loan/{phoneNumber}")
    public ResponseEntity getLoanDetails(@PathVariable(value = "phoneNumber") String phoneNumber){
        List<LoanApplication> loanList;
        try{
            Customer customer = loanDeskService.findCustomer(phoneNumber);
            if(customer!=null){
                loanList = loanDeskService.getLoanApplications(customer);
                CustomerLoanDetails customerLoanDetails = new CustomerLoanDetails();
                customerLoanDetails.setCustomer(customer);
                customerLoanDetails.setLoans(loanList);

                return ResponseEntity.ok(customerLoanDetails);

            }else{
                return ResponseEntity.badRequest()
                        .body("Customer details not found with phone number "+phoneNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/customer")
    public ResponseEntity createCustomer(@RequestBody String req){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode customerNode = objectMapper.readTree(req);
            Customer customer = loanDeskService.createCustomer(customerNode);
            if(customer!=null){
                String pwd = loanDeskService.generateLoginPassword();
                customer.setPassword(pwd);
                return ResponseEntity.ok(customer);
            }
            else{
                String phoneNum = customerNode.get("phoneNumber").asText();
                return ResponseEntity.badRequest().body("Customer already exist with "+phoneNum);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("customer/{phoneNum}")
    public ResponseEntity getCustomer(@PathVariable("phoneNum") String phoneNum){
        List<LoanApplication> loanList;
        try{
            Customer customer = loanDeskService.findCustomer(phoneNum);
            if(customer!=null){
                loanList = loanDeskService.getLoanApplications(customer);
                CustomerLoanDetails customerLoanDetails = new CustomerLoanDetails();
                customerLoanDetails.setCustomer(customer);
                customerLoanDetails.setLoans(loanList);

                return ResponseEntity.ok(customerLoanDetails);
            }else {
                return ResponseEntity.badRequest()
                        .body("Customer details not found with phone number" + phoneNum);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Exception while getting customer details with phone number " + phoneNum);
        }

    }

    @PostMapping("login")
    public ResponseEntity processLogin(@RequestBody String loginReq){
        List<LoanApplication> loanList;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode reqNode = objectMapper.readTree(loginReq);
            String phoneNum = reqNode.get("phoneNumber").asText();
            String pwd = reqNode.get("password").asText();

            Customer customer = loanDeskService.validateLogin(phoneNum,pwd);
            if(customer!=null){
                loanList = loanDeskService.getLoanApplications(customer);
                CustomerLoanDetails customerLoanDetails = new CustomerLoanDetails();
                customerLoanDetails.setCustomer(customer);
                customerLoanDetails.setLoans(loanList);

                return ResponseEntity.ok(customerLoanDetails);
            }else {
                return ResponseEntity.badRequest()
                        .body("Customer details not found with phone number" + phoneNum);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Exception while getting customer details for " + loginReq);
        }

    }

    @PostMapping("password")
    public ResponseEntity resetPassword(@RequestBody String resetReq){
        List<LoanApplication> loanList;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode reqNode = objectMapper.readTree(resetReq);
            String phoneNum = reqNode.get("phoneNumber").asText();
            String otp = reqNode.get("oneTimePassword").asText();
            String newPwd = reqNode.get("newPassword").asText();

            Customer customer = loanDeskService.resetPassword(phoneNum,newPwd,otp);
            if(customer!=null){
                loanList = loanDeskService.getLoanApplications(customer);
                CustomerLoanDetails customerLoanDetails = new CustomerLoanDetails();
                customerLoanDetails.setCustomer(customer);
                customerLoanDetails.setLoans(loanList);

                return ResponseEntity.ok(customerLoanDetails);
            }else {
                return ResponseEntity.badRequest()
                        .body("Customer details not found with phone number" + phoneNum);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Exception while getting customer details for " + resetReq);
        }

    }
}
