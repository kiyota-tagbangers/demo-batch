# demo-batch

## Build in local

```shell
./mvnw clean package

cp ./target/demo-batch-*.jar .

# java -jar ./demo-batch-0.0.1-SNAPSHOT.jar
```

## CodeDeploy command for deploy

```shell
cp ./target/demo-batch-*jar sampleapp 

cp ./target/demo-batch-*jar sampleapp2 
```

### deploy sampleapp directory contents

```shell
output=$(aws deploy push \
  --application-name kiyota-codedeploy-test \
  --description "This is a revision for the application kiyota-codedeploy-test sampleapp" \
  --s3-location s3://kiyota-codedeploy-test-codedeploy-revision/demo-batch.zip \
  --source ./sampleapp | tee /dev/tty)

export ETAG_VALUE=$(echo "$output" | sed -n 's/.*eTag=\([^ ]*\).*/\1/p')

aws deploy create-deployment \
  --application-name kiyota-codedeploy-test \
  --s3-location bucket=kiyota-codedeploy-test-codedeploy-revision,key=demo-batch.zip,bundleType=zip,eTag=$ETAG_VALUE \
  --deployment-group-name kiyota-codedeploy-test \
  --description "for sampleapp $(date +'%Y-%m-%d-%H-%M-%S')"
```

### deploy sampleapp2 directory contents

```shell
output=$(aws deploy push \
  --application-name kiyota-codedeploy-test \
  --description "This is a revision for the application kiyota-codedeploy-test sampleapp2" \
  --s3-location s3://kiyota-codedeploy-test-codedeploy-revision/demo-batch.zip \
  --source ./sampleapp2 | tee /dev/tty)

export ETAG_VALUE=$(echo "$output" | sed -n 's/.*eTag=\([^ ]*\).*/\1/p')

aws deploy create-deployment \
  --application-name kiyota-codedeploy-test \
  --s3-location bucket=kiyota-codedeploy-test-codedeploy-revision,key=demo-batch.zip,bundleType=zip,eTag=$ETAG_VALUE \
  --deployment-group-name kiyota-codedeploy-test-second \
  --description "for sampleapp2 $(date +'%Y-%m-%d-%H-%M-%S')"
```

## CodeDeploy command snippet

```shell
$ sudo service codedeploy-agent status
The AWS CodeDeploy agent is running as PID 2052

$ sudo service codedeploy-agent stop
Stopping codedeploy-agent:

$ sudo service codedeploy-agent status
error: No AWS CodeDeploy agent running
```

## publish to GitHub Packages

you have to generate personal access token and set authentication info to maven settings in advance

[generate personal access token](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages)

[set authentication info to maven settings](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-with-a-personal-access-token)

```shell
code ~/.m2/settings.xml
```

publish to GitHub Packages

```shell
# -B：バックグラウンドで実行, -U：依存関係を最新化, source:jar：コンパイルして、JAR ファイルにパッケージ化
./mvnw -B -U clean source:jar deploy
```

you can access to published artifact

https://github.com/kiyota-tagbangers/demo-batch/packages
