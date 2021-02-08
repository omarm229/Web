package managedbean;

import staffUI.StaffCommandFactory;
import dto.StaffDTO;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "staff")
@SessionScoped
public class StaffBean implements Serializable
{

    private StaffDTO staff = null;
    private String name, phoneNumber, password, userName;
    private int id;

    public String viewStaff()
    {
        staff = (StaffDTO) StaffCommandFactory.createCommand(StaffCommandFactory.VIEW_STAFF, id)
                .execute();
        return "StaffAccount?faces-redirect=true";
    }
    
    public String deleteStaff(int staffID)
    {
        boolean ok = (boolean) StaffCommandFactory.createCommand(StaffCommandFactory.DELETE_STAFF, staffID)
                .execute();
        return "StaffAccount?faces-redirect=true";
    }
    
    public ArrayList<StaffDTO> viewAllStaff(int store)
    {
        ArrayList<StaffDTO> allStaff
                = (ArrayList<StaffDTO>) StaffCommandFactory
                        .createCommand(
                                StaffCommandFactory.VIEW_ALL_STAFF, store)
                        .execute();


        return allStaff;
    }
          
    public String getName()
    {
        return name;
    }
    
    public String getUsername()
    {
        return userName;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public void setUsername(String username)
    {
        this.userName = username;
    }

    public int getStaffId()
    {
        int iD = (int)StaffCommandFactory
                .createCommand(StaffCommandFactory.GET_ID, userName)
                .execute();
        this.id = iD;
        return id;
    }
    
    public String setId()
    {
        int iD = (int)StaffCommandFactory
                .createCommand(StaffCommandFactory.GET_ID, userName)
                .execute();
        this.id = iD;
        return viewStaff();
    }
    
    public String editStaff() throws NoSuchAlgorithmException
    {
        
        staff = (StaffDTO) StaffCommandFactory.createCommand(StaffCommandFactory.EDIT_STAFF, id, name, userName, password, phoneNumber)
                .execute();
        return "StaffAccount?faces-redirect=true";
    }

    public StaffDTO getStaffDetails()
    {
        return staff;
    }
   
}
