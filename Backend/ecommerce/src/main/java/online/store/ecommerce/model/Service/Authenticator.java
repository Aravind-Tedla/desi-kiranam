package online.store.ecommerce.model.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import online.store.ecommerce.model.Entites.Customer;

import online.store.ecommerce.model.Request.SignInRequestJson;

//This class deal with the Authentication logic
public class Authenticator {
     private final BCryptPasswordEncoder passwordEncoder;

    public Authenticator() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    //This method validates customer  details
    public boolean customerDetailsValidator
    (SignInRequestJson signInRequestJson, Customer customer){
        if (signInRequestJson == null || customer == null) {
            return false;
        }
        // Compare the provided plain-text password with the stored encrypted password
        return signInRequestJson.getEmail().equalsIgnoreCase(customer.getEmail()) &&
                passwordEncoder.matches(signInRequestJson.getPassword(), customer.getPassword());
    }
    
   
}
