package manager;

import dto.StaffDTO;
import gateway.StaffGateway;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class StaffManager
{
    private final StaffGateway gateway = new StaffGateway();
    
    public StaffDTO viewStaff(int staffID)
    {
        return gateway.viewStaff(staffID);
    }
    
    public int getID(String userName)
    {
         return gateway.getId(userName);
    }
    
    public StaffDTO editStaff(int staffID, String name, String userName, String password, String phoneNumber) throws NoSuchAlgorithmException
    {
        return gateway.editStaff(staffID, name, userName, password, phoneNumber);
    }


    public boolean registerStaff(String userName, String name, int storeID, String password1, String password2, int jobApplicantID, String phoneNumber) throws NoSuchAlgorithmException
    {
        return gateway.registerStaff(userName, name, storeID, password1, password2, jobApplicantID, phoneNumber);
    }

    public Boolean loginStaff(String userName, String password, int storeID)
    {
        return gateway.loginStaff(userName, password, storeID);
    }
    
    public boolean deleteStaff(int staffID)
    {
        return gateway.deleteStaff(staffID);
    }
        
    public ArrayList<StaffDTO> viewAllStaff(int storeID)
    {
        return gateway.viewAllStaff(storeID);
    }
}

