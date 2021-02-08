package staffUI;


import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.StaffManager;

public class EditStaffCommand implements StaffCommand
{

    private final int staffID;
    private final String name,userName, password, phoneNumber;
    private final StaffManager staffMgr;

    public EditStaffCommand(int staffID, String name, String userName, String password, String phoneNumber)throws NoSuchAlgorithmException
    {
        this.staffID = staffID;
        this.name = name;
        this.userName = userName;
        this.password = password;
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
        try
        {
            return staffMgr.editStaff(staffID,name,userName, password, phoneNumber);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(EditStaffCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
