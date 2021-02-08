package customerUI;

import manager.CustomerManager;

public class ViewCustomerCommand implements CustomerCommand
{

    private final int custID;
    private final CustomerManager custMgr;

    public ViewCustomerCommand(int custID)
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
       return custMgr.viewCustomer(custID);
    }
}
