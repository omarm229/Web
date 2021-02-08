package staffUI;

import manager.StaffManager;

public class DeleteStaffCommand implements StaffCommand
{

    private final int staffID;
    private final StaffManager staffMgr;

    public DeleteStaffCommand(int staffID)
    {
        this.staffID = staffID;
        staffMgr = new StaffManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
       return staffMgr.deleteStaff(staffID);
    }
}
