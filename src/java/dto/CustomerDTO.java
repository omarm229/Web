package dto;

public class CustomerDTO
{

    private final int id;
    private final String name;
    private final String userName;
    private final String password;
    private final String address;
    private final String country;
    private final String postCode;

    public CustomerDTO(int id, String userName, String name, String address, String password, String country, String postCode)
    {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.password = password;
        this.country = country;
        this.postCode = postCode;
    }

    public int getId()
    {
        return id;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCountry()
    {
        return country;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPostCode()
    {
        return postCode;
    }
}
