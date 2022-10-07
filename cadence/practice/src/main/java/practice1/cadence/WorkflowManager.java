package practice1.cadence;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.client.WorkflowOptions;
import practice1.IHelloWorld;

public class WorkflowManager {
    public void startSayHelloJob(){
        WorkflowClient workflowClient = WorkflowClient.newInstance("test-domain", new WorkflowClientOptions.Builder().build());

        // Create a workflow stub.
        IHelloWorld workflow = workflowClient.newWorkflowStub(IHelloWorld.class, new WorkflowOptions.Builder().setTaskList("HelloWorldTaskList").build());

        WorkflowExecution workflowExecution = WorkflowClient.start(()->workflow.sayHello("bisvrup"));

        System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
    }
}
