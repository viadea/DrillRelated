# Apache Drill related scripts

## 1. HealthCheck.java

It will connect to each drillbit using JDBC and run a simple query to do the healthcheck for each drillbit.
### Compile:

```shell
javac HealthCheck.java
```

### Usage:

```shell
java -cp /opt/mapr/drill/drill-1.4.0/jars/jdbc-driver/drill-jdbc-all-1.4.0.jar:. HealthCheck <username> <password> <Initial_Host> <Drill_Port>
```
Default values for username and password are: mapr/mapr

Default value for Initial_Host is : localhost

Default value for Drill_Port is: 31010

### Example:
```shell
# java -cp /opt/mapr/drill/drill-1.4.0/jars/jdbc-driver/drill-jdbc-all-1.4.0.jar:. HealthCheck

Step 1. Creating Drill Connection to : jdbc:drill:drillbit=localhost:31010
Step 2. Fetching all drillbits
Connecting to v2.poc.com:31010
Successful!
Connecting to v3.poc.com:31010
Successful!
Connecting to v4.poc.com:31010
Successful!
Connecting to v1.poc.com:31010
Successful!
```

