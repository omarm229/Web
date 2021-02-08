package customerUI;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.CustomerManager;

public class RegisterCustomerCommand implements CustomerCommand
{

    
    private final String name, userName, address, country, password1, password2, postCode;
    private final CustomerManager custMgr;

    public RegisterCustomerCommand(String name, String userName, String address, String country, String password1, String password2, String postCode) throws NoSuchAlgorithmException
    
    {
        this.name = name;
        this.userName = userName;
        this.address = address;
        this.country = country;
        this.password1 = password1;
        this.password2 = password2;
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
        try {
            return custMgr.registerCustomer(name, userName, address, country, password1, password2, postCode);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterCustomerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
