package staffUI;

import manager.StaffManager;

public class getIdCommand implements StaffCommand
{

    private final String userName;
    private final StaffManager staffMgr;

    public getIdCommand(String userName)
    {
        this.userName = userName;
        staffMgr = new StaffManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
       return staffMgr.getID(userName);
    }
}
