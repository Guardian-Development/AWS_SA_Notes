package BJSS.aws_training;

public interface IProvideDownstreamAnalysis<T>
{
    void postMessageForAnalysis(T message);
}
