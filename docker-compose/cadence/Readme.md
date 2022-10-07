## Commands to get started

1. docker-compose up
2. Create a domain ```docker run --network=host --rm ubercadence/cli:master --do test-domain domain register -rd 1```
3. Check if the domain was actually created ```docker run --network=host --rm ubercadence/cli:master --do test-domain workflow start --tasklist HelloWorldTaskList --workflow_type IHelloWorld::sayHello --execution_timeout 3600 --input \"World\"```
4. Run the MainApplication file in your Java project. It would register the workflow.
5. Execute an instance of the workflow ```docker run --network=host --rm ubercadence/cli:master --do test-domain-staging domain describe```
6. The UI can be seen here, http://localhost:8088/domains


