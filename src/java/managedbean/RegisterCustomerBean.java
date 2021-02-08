package managedbean;

import customerUI.CustomerCommandFactory;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "custRegister")
@SessionScoped
public class RegisterCustomerBean implements Serializable
{

    private int custID;
    private String name, userName, address, country, password1, password2, postCode;

    public RegisterCustomerBean()
    {
    }

    public String register() throws NoSuchAlgorithmException
    {
        

        if ((boolean)CustomerCommandFactory
                .createCommand(CustomerCommandFactory.REGISTER_CUSTOMER, name, userName, address, country, password1, password2, postCode)
                .execute())
        {
            return "custLogin?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials are not correct"));
            return null;
        }
    }

    public int getCustID()
    {
        return custID;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getName()
    {
        return name;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPassword1()
    {
        return password1;
    }
    public String getPassword2()
    {
        return password2;
    }

    public String getCountry()
    {
        return country;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public void setUserName(String username)
    {
        this.userName = username;
    }
    
    public void setPostCode (String postCode)
    {
        this.postCode = postCode;
    }
    
    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPassword1(String password1)
    {
        this.password1 = password1;
    }
    
    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public void setCustID(int custID)
    {
        this.custID = custID;
    }
}
