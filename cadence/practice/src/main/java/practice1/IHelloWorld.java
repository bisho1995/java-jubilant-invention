package practice1;

import com.uber.cadence.workflow.WorkflowMethod;

public interface IHelloWorld {
    @WorkflowMethod(
            name = "sayHello",
            executionStartToCloseTimeoutSeconds = 120
    )
    String sayHello(String name);
}
