package manager;

import dto.CustomerDTO;
import gateway.CustomerGateway;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class CustomerManager
{
    private final CustomerGateway gateway = new CustomerGateway();
    
    public CustomerDTO viewCustomer(int custID)
    {
        return gateway.viewCustomer(custID);
    }

    public CustomerDTO editCustomer(int custID, String name, String userName, String address, String country, String password, String postCode) throws NoSuchAlgorithmException
    {
        return gateway.editCustomer(custID, name, userName, address, country, password, postCode);
    }

    public Boolean registerCustomer(String name, String userName, String address, String country, String password1, String password2, String postCode) throws NoSuchAlgorithmException
    {
        return gateway.registerCustomer(name, userName, address, country, password1, password2, postCode);
    }

    public Boolean loginCustomer(String userName, String password)
    {
        return gateway.loginCustomer(userName, password);
    }
    
    public int getID(String userName)
    {
        return gateway.getId(userName);
    }
    
     public boolean deleteCustomer(int custID)
    {
       return gateway.deleteCustomer(custID);
    }
     
     public ArrayList<CustomerDTO> viewAllCustomers()
    {
        return gateway.viewAllCustomers();
    }

}
