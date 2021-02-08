package dto;


public class CustomerOrderDTO
{

    private final int id;
    private final float total;
    private final int customerID;

    public CustomerOrderDTO(int id, float total, int customerID)
    {
        this.id = id;
        this.total = total;
        this.customerID = customerID;
    }

    public int getId()
    {
        return id;
    }

    public float getTotal()
    {
        return total;
    }

   
    
    public int getCustomerID()
    {
        return customerID;
    }

}
