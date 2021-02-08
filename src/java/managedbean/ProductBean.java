package managedbean;

import staffUI.StaffCommandFactory;
import dto.StoreProductDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "product")
@SessionScoped
public class ProductBean implements Serializable
{

    private StoreProductDTO product = null;
    private int id = 1;

    
    public String deleteProduct(int prodID)
    {
        boolean ok = (boolean) StaffCommandFactory.createCommand(StaffCommandFactory.DELETE_PRODUCT, prodID)
                .execute();
        return "StaffProducts?faces-redirect=true";
    }
    
    public ArrayList<StoreProductDTO> viewAllProducts()
    {
        ArrayList<StoreProductDTO> products = (ArrayList<StoreProductDTO>) StaffCommandFactory
                        .createCommand(
                                StaffCommandFactory.VIEW_ALL_PRODUCTS, id)
                        .execute();


        return products;
    }
          
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getStoreId(int id)
    {
        this.id = id;
        return "StaffProducts?faces-redirect=true";
    }
    

    public StoreProductDTO getProductDetails()
    {
        return product;
    }

    
}
