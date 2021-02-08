package customerUI;

import manager.OrderLineManager;

public class ViewOrderLineCommand implements CustomerCommand
{

    private final int orderID;
    private final OrderLineManager orderMgr;

    public ViewOrderLineCommand(int orderID)
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
        return orderMgr.viewOrderLine(orderID);
    }
}
