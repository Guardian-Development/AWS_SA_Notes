package BJSS.aws_training;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;

public class KinesisDownstreamAnalysisProvider implements IProvideDownstreamAnalysis<Tweet>
{
    private static ObjectMapper mapper = new ObjectMapper();

    private AmazonKinesis kinesisClient;
    private String kinesisStreamName;

    KinesisDownstreamAnalysisProvider(String awsRegion,
                                      String awsAccessKey,
                                      String awsSecretKey,
                                      String kinesisStreamName)
    {
        AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();
        clientBuilder.setRegion(awsRegion);
        clientBuilder.setCredentials(
                new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsAccessKey, awsSecretKey)));
        clientBuilder.setClientConfiguration(new ClientConfiguration());

        kinesisClient = clientBuilder.build();
        this.kinesisStreamName = kinesisStreamName;
    }

    public void postMessageForAnalysis(Tweet message)
    {
        try
        {
            String json = mapper.writeValueAsString(message);

            PutRecordRequest putRecordRequest = new PutRecordRequest();
            putRecordRequest.setStreamName(kinesisStreamName);
            putRecordRequest.setPartitionKey(String.format("partitionKey-%d", 0));
            putRecordRequest.setData(ByteBuffer.wrap(json.getBytes()));

            PutRecordResult result = kinesisClient.putRecord(putRecordRequest);
            System.out.println("Successfully published Tweet with Id:" + result.getSequenceNumber());
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }
}
