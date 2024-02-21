#!/bin/bash

set -e
echo 'application start (scripts directory)' > /tmp/run.log
java -jar /var/run/sampleapp/demo-batch-0.0.4.jar
