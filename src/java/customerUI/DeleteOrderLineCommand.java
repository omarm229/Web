package customerUI;

import manager.OrderLineManager;


public class DeleteOrderLineCommand implements CustomerCommand
{

    private final int orderID;
    private final OrderLineManager orderMgr;
   

    public DeleteOrderLineCommand(int orderID)
    {
        this.orderID = orderID;
        orderMgr = new OrderLineManager();
    }
    
    

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return orderMgr.deleteOrderLine(orderID);
    }
   
}
