package manager;

import dto.StoreProductDTO;
import gateway.StoreProductGateway;
import java.util.ArrayList;


public class StoreProductManager
{
    private final StoreProductGateway gateway = new StoreProductGateway();
    
    public ArrayList<StoreProductDTO> viewAllProducts(int storeID)
    {
        return gateway.viewAllProducts(storeID);
    }
        
    public boolean deleteProduct(int productID)
    {
        return gateway.deleteProduct(productID);
    }
}
