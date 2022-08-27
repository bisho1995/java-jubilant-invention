import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MainFlink {
    public static void main(String[] args) throws Exception {
        FlinkSimpleWindowedEventStreamExample.init();
//        FlinkPojoWindowedStreamExample.init();
//        FlinkSimpleWindowedTriggerExample.init();

//        System.out.println(System.getProperties());
    }
}
