# Getting Started > Your First Test Execution
[back](../README.md)

## Build the End-to-End (E2E) Automation Environment with Maven

In Terminal, navigate to the `edam-end-to-end-tests` root directory you cloned from git and and execute the following command:

`mvn clean install`

To verify that everything is working properly, navigate to the `edam-end-to-end-tests` root directory and execute the following command:
`mvn verify -Dedam.test.user="" -Dedam.test.pass="" -Dagent="chrome" -Dbrand="WS" -Denvironment="QA" -Dcustom.url="https://aemrck-vicn001.wsgc.com:8443" `

