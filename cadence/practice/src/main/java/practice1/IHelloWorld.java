package practice1;

import com.uber.cadence.workflow.WorkflowMethod;

public interface IHelloWorld {
    @WorkflowMethod
    String sayHello(String name);
}
