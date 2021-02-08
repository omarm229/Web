package managedbean;

import customerUI.CustomerCommandFactory;
import dto.CustomerDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import staffUI.StaffCommandFactory;

@Named(value = "customer")
@SessionScoped
public class CustomerBean implements Serializable
{

    private CustomerDTO customerDetails = null;
    private String  name, userName, address, country, password, postCode;
    private int id;

    public String viewCustomer()
    {
        customerDetails = (CustomerDTO) CustomerCommandFactory.createCommand(CustomerCommandFactory.VIEW_CUSTOMER,id)
                .execute();
        return "CustomerAccount?faces-redirect=true";
    }
    
    public String deleteCustomer(int custID)
    {
        boolean ok = (boolean) StaffCommandFactory.createCommand(StaffCommandFactory.DELETE_CUSTOMER, custID)
                .execute();
        return "allAccounts?faces-redirect=true";
    }
    
    public ArrayList<CustomerDTO> viewAllCustomers()
    {
        ArrayList<CustomerDTO> allCust
                = (ArrayList<CustomerDTO>) StaffCommandFactory
                        .createCommand(
                                StaffCommandFactory.VIEW_ALL_CUSTOMERS)
                        .execute();

        return allCust;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getUsername()
    {
        return userName;
    }

    public String getAddress()
    {
        return address;
    }
    
    public String getCountry()
    {
        return country;
    }

     public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPostCode()
    {
        return postCode;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }
    
    public void setUsername(String username)
    {
        this.userName = username;
    }


    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String setId()
    {
        int iD = (int)CustomerCommandFactory
                .createCommand(CustomerCommandFactory.GET_ID, userName)
                .execute();
        this.id = iD;
        return viewCustomer();
    }
    
    public int getCustId()
    {
        int iD = (int)CustomerCommandFactory
                .createCommand(CustomerCommandFactory.GET_ID, userName)
                .execute();
        this.id = iD;
        return id;
    }

    public String editCustomer()
    {
        customerDetails = (CustomerDTO) CustomerCommandFactory.createCommand(CustomerCommandFactory.EDIT_CUSTOMER, id, name, userName, address, country, password, postCode)
                .execute();
        return "CustomerAccount?faces-redirect=true";
    }

    public CustomerDTO getCustomerDetails()
    {
        return customerDetails;
    }
}
