package manager;

import dto.OrderLineDTO;
import gateway.OrderGateway;
import java.util.ArrayList;

public class OrderLineManager
{
    private final OrderGateway gateway = new OrderGateway();
    
    public int createOrderLine(int custID, int prodID)
    {
       return gateway.createOrderLine(custID, prodID);
    }
    
    public boolean deleteOrderLine(int orderID)
    {
        return gateway.deleteOrderLine(orderID);
    }
     
    public OrderLineDTO editOrderLine(int orderID, int numOfItem)
    {
        return gateway.editOrderLine(orderID, numOfItem);
    }
     
    public ArrayList<OrderLineDTO> viewOrderLine(int orderID)
    {
        return gateway.viewOrder(orderID);
    } 
    
    public ArrayList<OrderLineDTO> viewSales(String month)
    {
        return gateway.viewSales(month);
    } 
}
