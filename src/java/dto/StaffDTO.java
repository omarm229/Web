package dto;


public class StaffDTO
{

    private final int id;
    private final String name;
    private final String userName;
    private final String password;
    private final String phoneNumber;
    private final int storeID;
    private final int jobApplicantID;

    public StaffDTO(int id, String userName, String name, int storeID, String password, int jobApplicantID, String phoneNumber)
    {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.storeID = storeID;
        this.jobApplicantID = jobApplicantID;
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

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public String getPassword()
    {
        return password;
    }

    public int getJobApplicantID()
    {
        return jobApplicantID;
    }
    
    
}
