package online.store.ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import online.store.ecommerce.model.Entites.Customer;
import online.store.ecommerce.model.Repositories.CustomerRepository;
import online.store.ecommerce.model.Request.SignInRequestJson;
import online.store.ecommerce.model.Request.SignUpRequestJson;
import online.store.ecommerce.model.Response.AuthorizationResponseJson;
import online.store.ecommerce.model.Service.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(path="/OSD")

public class AuthController
{
    //Defining all response attributes
    private AuthorizationResponseJson authorizationResponseJson;
    public static final String SIGN_IN_SUCCESS_CODE = "200";
    public static final String SIGN_IN_FAILURE_CODE = "404";
    public static final String SIGN_UP_SUCCESS_CODE = "201";
    public static final String SIGN_UP_FAILURE_CODE = "409";
    public static final String
    SIGN_IN_SUCCESS_DESCRIPTION = "Customer sucessfully validated";
    public static final String
    EMPLOY_SIGN_IN_SUCCESS_DESCRIPTION = "Employ sucessfully validated";
    public static final String
    SIGN_IN_FAILURE_DESCRIPTION = "Please check the login credentials";
    public static final String
    SIGN_UP_SUCCESS_DESCRIPTION = "Customer sucessfully Created";
    public static final String
    SIGN_UP_FAILURE_DESCRIPTION = "Customer already exsists";
    private Map<String, String> responseValuesMap = 
    new HashMap<String, String>(){{
        put(SIGN_IN_SUCCESS_CODE, SIGN_IN_SUCCESS_DESCRIPTION);
        put(SIGN_IN_FAILURE_CODE, SIGN_IN_FAILURE_DESCRIPTION);
        put(SIGN_UP_SUCCESS_CODE, SIGN_UP_SUCCESS_DESCRIPTION);
        put(SIGN_UP_FAILURE_CODE, SIGN_UP_FAILURE_DESCRIPTION);
    }};

    @Autowired
    private CustomerRepository customerRepository;
   
    //This method takes care of the customer sign in part
    @PostMapping(path="/customer/signIn")
    public @ResponseBody AuthorizationResponseJson signInCustomer
    (@RequestBody SignInRequestJson signInRequestJson){
        try{
            Customer customer;
            customer = customerRepository.findByEmail
            (signInRequestJson.getEmail());
            authorizationResponseJson = new AuthorizationResponseJson
            (SIGN_IN_FAILURE_CODE,
            responseValuesMap.get(SIGN_IN_FAILURE_CODE));
            Authenticator authenticator = new Authenticator();
            // Validating customer details in Authenticator class
            if(customer != null && authenticator.customerDetailsValidator
            (signInRequestJson,customer)){
                authorizationResponseJson = new AuthorizationResponseJson
                (SIGN_IN_SUCCESS_CODE,
                responseValuesMap.get(SIGN_IN_SUCCESS_CODE),
                customer.getEmail().toLowerCase(),
                customer.getName(),
                customer.getCustomerId());
            }
            return authorizationResponseJson;
        }
        catch(Exception e){
            authorizationResponseJson = new AuthorizationResponseJson
            (responseValuesMap.get(SIGN_IN_FAILURE_CODE),
            e.getMessage());
            return authorizationResponseJson;
        }
    }
    //This method takes care of the customer sign up part
    @PostMapping(path = "/customer/signUp")
    public @ResponseBody AuthorizationResponseJson signUpCustomer
    (@RequestBody SignUpRequestJson signUpRequestJson){
        try{
            authorizationResponseJson = new AuthorizationResponseJson
            (SIGN_UP_FAILURE_CODE,
            responseValuesMap.get(SIGN_UP_FAILURE_CODE));
            if(customerRepository.findByEmail
            (signUpRequestJson.getEmail()) == null){
                //Creating a new customer if the user details not available
                Customer newCustomer = new Customer();
                newCustomer.setEmail(signUpRequestJson.getEmail());
                newCustomer.setName(signUpRequestJson.getName());
                newCustomer.setPassword(signUpRequestJson.getPassword());
                Long id = customerRepository.save(newCustomer).getCustomerId();
                authorizationResponseJson = new AuthorizationResponseJson
                (SIGN_UP_SUCCESS_CODE,
                responseValuesMap.get(SIGN_UP_SUCCESS_CODE),
                signUpRequestJson.getEmail(),
                signUpRequestJson.getName(),
                id
                );
            }
            return authorizationResponseJson;
        }
        catch (Exception e ){
            authorizationResponseJson = new AuthorizationResponseJson
            (responseValuesMap.get(SIGN_UP_FAILURE_CODE),
            e.getMessage());
            return authorizationResponseJson;
        }
    }

}
