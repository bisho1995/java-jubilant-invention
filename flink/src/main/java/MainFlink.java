import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MainFlink {
    public static void main(String[] args) throws Exception {
        FlinkPojoWindowedStreamExample.init();
//        FlinkSimpleWindowedEventStreamExample.init();

//        System.out.println(System.getProperties());
    }
}
