package managedbean;

import staffUI.StaffCommandFactory;
import dto.StoreDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "store")
@RequestScoped
public class StoreBean implements Serializable
{


    private StoreDTO store = null;
   
    
    public ArrayList<StoreDTO> viewStore()
    {
        ArrayList<StoreDTO> stores
                = (ArrayList<StoreDTO>) StaffCommandFactory
                        .createCommand(
                                StaffCommandFactory.VIEW_STORES)
                        .execute();


        return stores;
    }
          
    public StoreDTO getProductDetails()
    {
        return store;
    }

    public void setProductDetails(StoreDTO store)
    {
        this.store = store;
    }

   
}
