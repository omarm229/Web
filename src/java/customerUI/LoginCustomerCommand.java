package customerUI;

import manager.CustomerManager;

public class LoginCustomerCommand implements CustomerCommand
{

    private final String userName, password;
    private final CustomerManager custMgr;

    public LoginCustomerCommand(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
        custMgr = new CustomerManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        return custMgr.loginCustomer(userName, password);
    }
}

