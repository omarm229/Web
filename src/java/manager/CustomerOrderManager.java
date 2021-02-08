package manager;

import dto.CustomerOrderDTO;
import gateway.CustomerOrderGateway;

public class CustomerOrderManager
{
    private final CustomerOrderGateway gateway = new CustomerOrderGateway();
    
    public boolean createOrder(CustomerOrderDTO order)
    {
        return gateway.createOrder(order);
    }
    
    public boolean deleteOrder(int orderID)
    {
        return gateway.deleteOrder(orderID);
    }
     
    public CustomerOrderDTO viewOrder(int orderID)
    {
        return gateway.viewOrder(orderID);
    }
    
    

}
