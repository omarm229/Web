package customerUI;

import dto.CustomerOrderDTO;
import dto.OrderLineDTO;
import java.security.NoSuchAlgorithmException;

public class CustomerCommandFactory
{

    public static final int REGISTER_CUSTOMER = 1;
    public static final int VIEW_CUSTOMER = 2;
    public static final int LOGIN_CUSTOMER = 3;
    public static final int EDIT_CUSTOMER = 4;
    public static final int CREATE_ORDER = 5;
    public static final int EDIT_ORDER = 6;
    public static final int CREATE_ORDER_LINE = 7;
    public static final int VIEW_ORDER_LINE = 8;
    public static final int VIEW_ORDER = 9;
    public static final int VIEW_ALL_PRODUCTS = 10;
    public static final int DELETE_ORDER_LINE = 11;
    public static final int DELETE_ORDER = 12;
    public static final int GET_ID = 13;

    public static CustomerCommand createCommand(int commandType, String name, String userName, String address, String country, String password1, String password2, String postCode) throws NoSuchAlgorithmException
    {
        switch (commandType)
        {
            case REGISTER_CUSTOMER:
                return new RegisterCustomerCommand(name, userName, address, country, password1, password2, postCode);
            default:
                return null;
        }
    }

    public static CustomerCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case VIEW_ALL_PRODUCTS:
                return new ViewAllProductsCommand(id);
            case VIEW_CUSTOMER:
                return new ViewCustomerCommand(id);
            case DELETE_ORDER:
                return new DeleteOrderCommand(id);
            case DELETE_ORDER_LINE:
                return new DeleteOrderLineCommand(id);
            case VIEW_ORDER:
                return new ViewOrderCommand(id);
            case VIEW_ORDER_LINE:
                return new ViewOrderLineCommand(id);
            default:
                return null;
        }
    }

    public static CustomerCommand createCommand(int commandType, String userName, String password)
    {
        switch (commandType)
        {
            case LOGIN_CUSTOMER:
                return new LoginCustomerCommand(userName, password);
            default:
                return null;
        }
    }
    
    public static CustomerCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            
            default:
                return null;
        }
    }
    
    public static CustomerCommand createCommand(int commandType, CustomerOrderDTO orderDTO)
    {
        switch (commandType)
        {
            case CREATE_ORDER:
                return new CreateOrderCommand(orderDTO);
            default:
                return null;
        }
    }
    
    public static CustomerCommand createCommand(int commandType, int custID, int prodID)
    {
        switch (commandType)
        {
            case EDIT_ORDER:
                return new EditOrderCommand(custID, prodID);
            case CREATE_ORDER_LINE:
                return new CreateOrderLineCommand(custID, prodID);
            default:
                return null;
        }
    }
    
     public static CustomerCommand createCommand(int commandType, String userName)
    {
        switch (commandType)
        {
            case GET_ID:
                return new GetIdCommand(userName);
            default:
                return null;
        }
    }
    
    public static CustomerCommand createCommand(int commandType, int custID, String name, String userName, String address, String country, String password, String postCode)
    {
        switch (commandType)
        {
            case EDIT_CUSTOMER:
                return new EditCustomerCommand(custID, name, userName, address, country, password, postCode);
            default:
                return null;
        }
    }
    
     public static CustomerCommand createCommand(int commandType, int orderID, int storeProductID, int numOfItem)
    {
        switch (commandType)
        {
            
            default:
                return null;
        }
    }
    
}
