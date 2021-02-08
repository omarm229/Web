package staffUI;

import manager.CustomerManager;

public class DeleteCustomerCommand implements StaffCommand
{

    private final int custID;
    private final CustomerManager custMgr;

    public DeleteCustomerCommand(int custID)
    {
        this.custID = custID;
        custMgr = new CustomerManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
       return custMgr.deleteCustomer(custID);
    }
}

