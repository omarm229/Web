package dto;


public class StoreProductDTO
{

    private final int id;
    private final int numAvailable;
    private final float price;
    private final int storeID;
    private final int productID;
    private final String description, name;

    public StoreProductDTO(int id, int numAvailable, float price, String description, String name, int productID, int storeID)
    {
        this.id = id;
        this.price = price;
        this.numAvailable = numAvailable;
        this.description = description;
        this.name = name;
        this.productID = productID;
        this.storeID = storeID;
    }

    public int getId()
    {
        return id;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }

    public float getPrice()
    {
        return price;
    }

    public int getStoreID()
    {
        return storeID;
    }
    
    public int getProductID()
    {
        return productID;
    }
    
    public int getNumAvailable()
    {
        return numAvailable;
    }

}
