package customerUI;

import manager.CustomerOrderManager;


public class DeleteOrderCommand implements CustomerCommand
{

    private final int orderID;
    private final CustomerOrderManager orderMgr;
   

    public DeleteOrderCommand(int orderID)
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
        return orderMgr.deleteOrder(orderID);
    }
   
}
