package practice1;

import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import practice1.cadence.WorkflowManager;


public class MainApplication {
    public static final String CADENCE_DOMAIN="test-domain";
    public static final String TASK_LIST ="HelloWorldTaskList";
    public static void main(String[] args) {
        Worker.Factory factory = new Worker.Factory(CADENCE_DOMAIN);
        Worker helloWorldWorker = factory.newWorker(TASK_LIST);

        helloWorldWorker.registerWorkflowImplementationTypes(HelloWorldImpl.class);
        factory.start();

        WorkflowManager workflowManager = new WorkflowManager();
        workflowManager.startSayHelloJob();
    }
}
