package customerUI;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.CustomerManager;

public class EditCustomerCommand implements CustomerCommand
{

    private final int custID;
    private final String name,userName, address, country, password, postCode;
    private final CustomerManager custMgr;

    public EditCustomerCommand(int custID,String name,String userName, String address, String country, String password, String postCode)
    {
        this.custID = custID;
        this.name = name;
        this.userName = userName;
        this.address = address;
        this.country = country;
        this.password = password;
        this.postCode = postCode;
        custMgr = new CustomerManager();
    }

    /**
     *
     * @return
     */
    @Override
    public Object execute()
    {
        try
        {
            return custMgr.editCustomer(custID,name,userName, address, country, password, postCode);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(EditCustomerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
