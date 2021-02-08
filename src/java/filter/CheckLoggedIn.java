package filter;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managedbean.LoginCustomerBean;
import managedbean.LoginStaffBean;

@WebFilter(filterName = "CheckLoggedIn", urlPatterns =
{
    "/*"
})
public class CheckLoggedIn implements Filter
{

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    @Inject
    LoginStaffBean staffb;
    @Inject
    LoginCustomerBean cust;

    public CheckLoggedIn()
    {
    }

    /*
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/faces/custLogin.xhtml";
        String registerURI = request.getContextPath() + "/faces/customerRegister.xhtml";
        String indexURI = request.getContextPath() + "/faces/index.xhtml";
        String staffURI = request.getContextPath() + "/faces/StaffLogin.xhtml";
        String staffEditURI = request.getContextPath() + "/faces/staffEdit.xhtml";
        String custEditURI = request.getContextPath() + "/faces/custEdit.xhtml";
        String staffHomeURI = request.getContextPath() + "/faces/staffHome.xhtml";
        String custHomeURI = request.getContextPath() + "/faces/custHome.xhtml";
        String salesURI = request.getContextPath() + "/faces/sales.xhtml";
        String staffRURI = request.getContextPath() + "/faces/staffRegister.xhtml";
        String staffProductsURI = request.getContextPath() + "/faces/StaffProducts.xhtml";
        String custProductsURI = request.getContextPath() + "/faces/CustomerProducts.xhtml";
        String custOrdersURI = request.getContextPath() + "/faces/CustomerOrders.xhtml";
        String custAccountURI = request.getContextPath() + "/faces/CustomerAccount.xhtml";
        String custAccount2URI = request.getContextPath() + "/faces/orderEdit.xhtml";
        String staffAccountURI = request.getContextPath() + "/faces/StaffAccount.xhtml";
        String cartURI = request.getContextPath() + "/faces/cart.xhtml";
        String allAccountsURI = request.getContextPath() + "/faces/allAccounts.xhtml";
        String cssURI = request.getContextPath() + "/faces/javax.faces.resources/css/vowelCount.css";

        
        boolean loggedIn = staffb.credentialsAreOK();
        boolean loggedInCust = cust.credentialsAreOK();
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean indexRequest = request.getRequestURI().equals(indexURI);
        boolean staffLoginRequest = request.getRequestURI().equals(staffURI);
        boolean salesRequest = request.getRequestURI().equals(salesURI);
        boolean cartRequest = request.getRequestURI().equals(cartURI);
        boolean allAccountsRequest = request.getRequestURI().equals(allAccountsURI);
        boolean staffHomeRequest = request.getRequestURI().equals(staffHomeURI);
        boolean custHomeRequest = request.getRequestURI().equals(custHomeURI);
        boolean staffEditRequest = request.getRequestURI().equals(staffEditURI);
        boolean custEditRequest = request.getRequestURI().equals(custEditURI);
        boolean staffProductsRequest = request.getRequestURI().equals(staffProductsURI);
        boolean custProductsRequest = request.getRequestURI().equals(custProductsURI);
        boolean custOrdersRequest = request.getRequestURI().equals(custOrdersURI);
        boolean custAccountRequest = request.getRequestURI().equals(custAccountURI);
        boolean custAccountRequest2 = request.getRequestURI().equals(custAccount2URI);
        boolean staffAccountRequest = request.getRequestURI().equals(staffAccountURI);
        boolean staffRegisterRequest = request.getRequestURI().equals(staffRURI);
        boolean resourceRequest = request.getRequestURI().equals(cssURI);

        if (loginRequest || registerRequest || resourceRequest || indexRequest || staffLoginRequest || staffRegisterRequest)
        {
            chain.doFilter(request, response);
        }
        else if (staffb.credentialsAreOK()){
            if (resourceRequest || staffEditRequest || staffHomeRequest || staffAccountRequest || staffProductsRequest || allAccountsRequest || salesRequest )
            {
            chain.doFilter(request, response);
            }
        }
        else if (cust.credentialsAreOK()){
            if (custEditRequest || custAccountRequest2 || resourceRequest || custHomeRequest || custAccountRequest || custOrdersRequest || custProductsRequest || cartRequest )
            {
            chain.doFilter(request, response);
            }
        }
       
        else
        {
            response.sendRedirect(indexURI);
        }
    }

    /*
     * Destroy method for this filter
     */
    @Override
    public void destroy()
    {
        this.filterConfig = null;
    }

    /*
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);
    }

}
