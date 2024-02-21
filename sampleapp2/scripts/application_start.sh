#!/bin/bash

set -e
echo 'application start (scripts directory)' > /tmp/run.log
java -jar /var/run/sampleapp2/demo-batch-0.0.4.jar
