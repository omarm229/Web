package staffUI;

import manager.StaffManager;

public class LoginStaffCommand implements StaffCommand
{

    private final String userName, password;
    private final int storeID;
    private final StaffManager staffMgr;

    public LoginStaffCommand(String userName, String password, int storeID)
    {
        this.userName = userName;
        this.password = password;
        this.storeID = storeID;
        staffMgr = new StaffManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return staffMgr.loginStaff(userName, password, storeID);
    }
}

