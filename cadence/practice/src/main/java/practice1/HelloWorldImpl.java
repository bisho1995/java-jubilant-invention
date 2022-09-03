package practice1;

import com.uber.cadence.workflow.Workflow;
import org.slf4j.Logger;

public class HelloWorldImpl implements IHelloWorld {
    public static final Logger logger = Workflow.getLogger(HelloWorldImpl.class);
    @Override
    public String sayHello(String name) {
        logger.info("Hello {}!", name);


        return "Hello "+ name+"!!";
    }
}
