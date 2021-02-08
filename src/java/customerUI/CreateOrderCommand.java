package customerUI;

import manager.CustomerOrderManager;
import dto.CustomerOrderDTO;

public class CreateOrderCommand implements CustomerCommand
{
    private final CustomerOrderDTO orderDTO;
    private final CustomerOrderManager orderMgr;
    
    public CreateOrderCommand(CustomerOrderDTO orderDTO){
        this.orderDTO = orderDTO;
        orderMgr = new CustomerOrderManager();
    }
      /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        if (orderMgr.createOrder(orderDTO))
        {
            return orderMgr.viewOrder(orderDTO.getId());
        }
        return null;
    }
}
