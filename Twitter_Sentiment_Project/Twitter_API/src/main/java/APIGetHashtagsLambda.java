import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;

public class APIGetHashtagsLambda implements RequestHandler<EmptyRequest, HashtagsResponse>
{
    public HashtagsResponse handleRequest(EmptyRequest emptyRequest, Context context) {
        HashtagsResponse response = new HashtagsResponse();
        List<String> hashTags = new ArrayList<String>();
        hashTags.add("USA");
        hashTags.add("Test");
        response.setHashtags(hashTags);

        return response;
    }
}
