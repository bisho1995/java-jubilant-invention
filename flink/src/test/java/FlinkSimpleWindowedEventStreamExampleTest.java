import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.DiscardingSink;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FlinkSimpleWindowedEventStreamExampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testProcessStreamTc1() throws Exception {
        StreamExecutionEnvironment env=FlinkSimpleWindowedEventStreamExample.getEnvironment();
        DataStreamSource<Integer> stream = env.fromElements(2, 3, 5, 10, 11, 12, 4, 1, 2, 55, 1);
        SingleOutputStreamOperator<List<Integer>> processedStream = FlinkSimpleWindowedEventStreamExample.processStream(stream);

        processedStream.addSink(new RichSinkFunction<List<Integer>>() {
            @Override
            public void invoke(List<Integer> value, Context context) throws Exception {
                super.invoke(value, context);
            }
        });

        env.execute();
    }
}