
```shell
./mvnw clean package

cp ./target/demo-batch-0.0.1-SNAPSHOT.jar .

java -jar ./demo-batch-0.0.1-SNAPSHOT.jar 
```


```shell
aws deploy push \
  --application-name kiyota-codedeploy-test \
  --description "This is a revision for the application kiyota-codedeploy-test" \
  --s3-location s3://kiyota-codedeploy-test-codedeploy-revision/demo-batch.zip \
  --source .

aws deploy create-deployment \
  --application-name kiyota-codedeploy-test \
  --s3-location bucket=kiyota-codedeploy-test-codedeploy-revision,key=demo-batch.zip,bundleType=zip,eTag=1f0eab56336ff97fda5bd2e4d7616ff7-4 \
  --deployment-group-name kiyota-codedeploy-test \
  --description "hello 3"
```

```shell
$ sudo service codedeploy-agent status
The AWS CodeDeploy agent is running as PID 2052
$ sudo service codedeploy-agent stop
Stopping codedeploy-agent:

$ sudo service codedeploy-agent status
error: No AWS CodeDeploy agent running
```