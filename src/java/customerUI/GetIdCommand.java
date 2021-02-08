package customerUI;


import manager.CustomerManager;

public class GetIdCommand implements CustomerCommand
{

    private final String userName;
    private final CustomerManager custMgr;

    public GetIdCommand(String userName)
    {
        this.userName = userName;
        custMgr = new CustomerManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
       return custMgr.getID(userName);
    }
}
