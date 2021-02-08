package staffUI;

import manager.StoreProductManager;

public class DeleteProductCommand implements StaffCommand
{

    private final int prodID;
    private final StoreProductManager prodMgr;

    public DeleteProductCommand(int prodID)
    {
        this.prodID = prodID;
        prodMgr = new StoreProductManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
       return prodMgr.deleteProduct(prodID);
    }
}
