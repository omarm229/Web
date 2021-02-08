package customerUI;

import manager.OrderLineManager;

public class EditOrderCommand implements CustomerCommand
{

    private final int orderID, numOfItem;
    private final OrderLineManager orderLineMgr;

    public EditOrderCommand(int orderID, int numOfItem)
    {
        this.orderID = orderID;
        this.numOfItem = numOfItem;
        orderLineMgr = new OrderLineManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return orderLineMgr.editOrderLine(orderID, numOfItem);
    }
}
