package managedbean;

import customerUI.CustomerCommandFactory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "custLogin")
@SessionScoped
public class LoginCustomerBean implements Serializable
{

    private boolean credentialsOK = false;
    private String userName;
    private String password;

    public LoginCustomerBean()
    {
    }

    public String loginCustomer(String username, String passWord)
    {
        setPassword(passWord);
        setUsername(username);
        credentialsOK = (Boolean)CustomerCommandFactory
                .createCommand(CustomerCommandFactory.LOGIN_CUSTOMER, userName, password)
                .execute();
        if (credentialsOK)
        {
            return "custHome?faces-redirect=true";
        }
        else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct"));
            return null;
        }

    }

    private void clearCredentials()
    {
        this.userName = "";
        this.password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    

    public String getUsername()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String logOff()
    {
        clearCredentials();
        return "custLogin?faces-redirect=true";
    }
    
    public void setUsername(String username)
    {
        this.userName = username;
    }

    public void setPassword(String password)
    {
        try
        {
            byte[] hash
                    = MessageDigest.getInstance("SHA-256")
                            .digest(password.getBytes(StandardCharsets.UTF_8));

            this.password
                    = Base64.getEncoder().encodeToString(hash);

        }
        catch (NoSuchAlgorithmException ex)
        {
            this.password = "";
        }
    }

    public boolean credentialsAreOK() 
    {
        return credentialsOK;
    }
    
    

}
