package customerUI;

import manager.CustomerOrderManager;

public class ViewOrderCommand implements CustomerCommand
{

    private final int orderID;
    private final CustomerOrderManager orderMgr;

    public ViewOrderCommand(int orderID)
    {
        this.orderID = orderID;
        orderMgr = new CustomerOrderManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return orderMgr.viewOrder(orderID);
    }
}
