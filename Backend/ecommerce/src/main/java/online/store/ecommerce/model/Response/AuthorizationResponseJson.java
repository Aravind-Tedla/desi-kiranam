package online.store.ecommerce.model.Response;

public class AuthorizationResponseJson {
    
    private String email;
    private String name;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    private Long customerId;
    private String statusCode;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    private String statusMessage;
    
    public AuthorizationResponseJson(String statusCode,String statusMessage){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
    
    public AuthorizationResponseJson(String statusCode,
    String statusMessage,String email, String name){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.email = email;
        this.name = name;
    }
    
    public AuthorizationResponseJson(String statusCode,
    String statusMessage,String email, String name,Long customerId){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.email = email;
        this.name = name;
        this.customerId = customerId;
    }

    
}