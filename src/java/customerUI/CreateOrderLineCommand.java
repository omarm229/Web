package customerUI;

import manager.OrderLineManager;

public class CreateOrderLineCommand implements CustomerCommand
{
    private final OrderLineManager orderLineMgr;
    private final int custID, prodID;
    
    public CreateOrderLineCommand(int custID, int prodID){
        this.custID = custID;
        this.prodID = prodID;
        orderLineMgr = new OrderLineManager();
    }
      /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return orderLineMgr.createOrderLine(custID, prodID);
       
        
    }
}
