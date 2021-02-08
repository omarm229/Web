package managedbean;

import customerUI.CustomerCommandFactory;
import dto.CustomerOrderDTO;
import dto.OrderLineDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import staffUI.StaffCommandFactory;

@Named(value = "order")
@SessionScoped
public class OrderBean implements Serializable
{

    private CustomerOrderDTO order = null;
    private OrderLineDTO orderLine = null;
    private String month;
    private int orderID, orderLineID, numOfItem;

    public String viewOrder()
    {
        order = (CustomerOrderDTO) CustomerCommandFactory.createCommand(CustomerCommandFactory.VIEW_ORDER, orderID)
                .execute();
        return "CustomerOrders?faces-redirect=true";
    }
    
     public ArrayList<OrderLineDTO> viewSales()
    {
        ArrayList<OrderLineDTO> sales
                = (ArrayList<OrderLineDTO>) StaffCommandFactory
                        .createCommand(
                                StaffCommandFactory.VIEW_SALES, month)
                        .execute();

        return sales;
    }
     
    public String deleteOrder()
    {
        boolean deleteOK = (boolean) CustomerCommandFactory.createCommand(CustomerCommandFactory.DELETE_ORDER, orderID)
                .execute();
        return "custHome?faces-redirect=true";
    }
    
    public ArrayList<OrderLineDTO> viewOrderLine()
    {
        ArrayList<OrderLineDTO> allOrderLines = ( ArrayList<OrderLineDTO>) CustomerCommandFactory.createCommand(CustomerCommandFactory.VIEW_ORDER_LINE, orderID)
                .execute();
        return allOrderLines;
    }
    
    public String deleteOrderLine(int orderID)
    {
        boolean deleteOK = (boolean) CustomerCommandFactory.createCommand(CustomerCommandFactory.DELETE_ORDER_LINE, orderID)
                .execute();
        return "cart?faces-redirect=true";
    }
    
    public String setOrderLineID(int orderLineID)
    {
        this.orderLineID = orderLineID;
        return "orderEdit?faces-redirect=true";
    }
         
    public String editOrder()
    {
        orderLine = (OrderLineDTO) CustomerCommandFactory.createCommand(CustomerCommandFactory.EDIT_ORDER, orderLineID, numOfItem)
                .execute();
        return "cart?faces-redirect=true";
    }

    public CustomerOrderDTO getOrderDetails()
    {
        return order;
    }

    public void setOrderDetails(CustomerOrderDTO order)
    {
        order = (CustomerOrderDTO) CustomerCommandFactory.createCommand(CustomerCommandFactory.CREATE_ORDER, order)
                .execute();
        this.order = order;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public String getSetMonth(String month)
    {
        this.month = month;
        return "sales?faces-redirect=true";
    }
    
    public int getNumOfItem()
    {
        return numOfItem;
    }
    
    public void setNumOfItem(int numOfItem)
    {
        this.numOfItem = numOfItem;
    }
    
    public OrderLineDTO getOrderLine()
    {
        return orderLine;
    }

    public void createOrderLine(int custID, int prodID)
    {
        orderID = (int) CustomerCommandFactory.createCommand(CustomerCommandFactory.CREATE_ORDER_LINE, custID, prodID)
                .execute();
    }
}
