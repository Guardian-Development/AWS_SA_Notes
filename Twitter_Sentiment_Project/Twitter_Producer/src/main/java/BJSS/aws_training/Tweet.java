package BJSS.aws_training;

import java.util.List;

public class Tweet
{
    public String UserId;
    public String Body;
    public List<String> HashTags;

    public Tweet(String userId, String body, List<String> hashTags)
    {
        UserId = userId;
        Body = body;
        HashTags = hashTags;
    }
}
