package staffUI;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.StaffManager;

public class RegisterStaffCommand implements StaffCommand
{

   private final int storeID, jobApplicantID;
    private final String name, userName, password1, password2, phoneNumber;
    private final StaffManager staffMgr;

     public RegisterStaffCommand(String userName, String name, int storeID, String password1, String password2, int jobApplicantID, String phoneNumber) throws NoSuchAlgorithmException
    
    {
        
        this.name = name;
        this.userName = userName;
        this.jobApplicantID = jobApplicantID;
        this.storeID = storeID;
        this.password1 = password1;
        this.password2 = password2;
        this.phoneNumber = phoneNumber;
        staffMgr = new StaffManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        try {
            return staffMgr.registerStaff(userName, name, storeID, password1, password2, jobApplicantID, phoneNumber);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterStaffCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}