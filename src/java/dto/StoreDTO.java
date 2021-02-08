package dto;

public class StoreDTO
{

    private final int id;
    private final String name;
    private final String country;
    private final String postCode;

    public StoreDTO(int id,String name, String country, String postCode)
    {
        this.id = id;
        this.name = name;
        this.country= country;
        this.postCode = postCode;
    }

    

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getCountry()
    {
        return country;
    }

    public String getPostCode()
    {
        return postCode;
    }
}
