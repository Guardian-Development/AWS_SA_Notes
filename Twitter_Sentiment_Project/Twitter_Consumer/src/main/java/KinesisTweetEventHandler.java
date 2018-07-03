import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

public class KinesisTweetEventHandler implements RequestHandler<KinesisEvent, Void>
{
        public Void handleRequest(KinesisEvent event, Context context)
        {
            System.out.println("Record Size - " + event.getRecords().size());
            for(KinesisEvent.KinesisEventRecord rec : event.getRecords()) {
                System.out.println(rec.getKinesis().getSequenceNumber());
                System.out.println(new String(rec.getKinesis().getData().array()));
            }

            return null;
        }
}
