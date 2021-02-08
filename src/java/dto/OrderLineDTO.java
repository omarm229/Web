package dto;


public class OrderLineDTO
{

    private final int id;
    private final String dateOrdered, name;
    private final float total;
    private final int customerOrderID;
    private final int storeProductID;
    private final int numOfItem;

    public OrderLineDTO(int id, String dateOrdered, String name, float total, int customerOrderID, int storeProductID, int numOfItem)
    {
        this.id = id;
        this.dateOrdered = dateOrdered;
        this.name = name;
        this.total = total;
        this.customerOrderID = customerOrderID;
        this.storeProductID = storeProductID;
        this.numOfItem = numOfItem;
    }

    public int getId()
    {
        return id;
    }

    public float getTotal()
    {
        return total;
    }

    public String getDateOrdered()
    {
        return dateOrdered;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getStoreProductID()
    {
        return storeProductID;
    }
    
    public int getcustomerOrderID()
    {
        return customerOrderID;
    }
    
    public int getNumOfItem()
    {
        return numOfItem;
    }

}
