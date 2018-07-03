import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet
{
    public String UserId;
    public String Body;
    public Date CreatedDate;
    public List<String> HashTags;

    public Tweet(String userId, String body, Date createdDate, List<String> hashTags)
    {
        UserId = userId;
        Body = body;
        HashTags = hashTags;
        CreatedDate = createdDate;
    }

    Tweet()
    {
        this("", "", new Date(), new ArrayList<String>());
    }
}
