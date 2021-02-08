package customerUI;

import manager.StoreProductManager;

public class ViewAllProductsCommand implements CustomerCommand
{
    private final StoreProductManager prodMgr;
    private final int id;
    
    public ViewAllProductsCommand(int id)
    {
        this.id = id;
        prodMgr = new StoreProductManager();
    }
    
    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return prodMgr.viewAllProducts(id);
    }
}
