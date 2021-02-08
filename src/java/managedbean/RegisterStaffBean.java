package managedbean;

import staffUI.StaffCommandFactory;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "staffRegister")
@SessionScoped
public class RegisterStaffBean implements Serializable
{

    private int storeID, id, jobApplicantID;
    private String userName, name, password1, password2, phoneNumber;

    public RegisterStaffBean()
    {
    }

    public String register() throws NoSuchAlgorithmException
    {
        boolean dataOK = (Boolean)StaffCommandFactory
                .createCommand(StaffCommandFactory.REGISTER_STAFF, userName, name, storeID, password1, password2, jobApplicantID, phoneNumber)
                .execute();

        if (dataOK)
        {
            return "StaffLogin?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials are not correct"));
            return null;
        }
    }

    public int getId()
    {
        return id;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public String getPassword1()
    {
        return password1;
    }
    public String getPassword2()
    {
        return password2;
    }

    public int getJobApplicantID()
    {
        return jobApplicantID;
    }
    
    public void setStoreID(int storeID)
    {
        this.storeID = storeID;
    }
    
    public void setUserName(String username)
    {
        this.userName = username;
    }
    
    public void setJobApplicantID(int jobApplicantID)
    {
        this.jobApplicantID = jobApplicantID;
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
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
}
