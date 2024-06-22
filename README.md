# Hadoop Prime Number Finder

## Overview

Apache Hadoop is a platform that facilitates using a network of many computers to solve problems involving massive amounts of data and computation. It provides a software framework for distributed storage and processing of big data using the MapReduce programming model.

This project modifies a previous project to use Hadoop for distributed storage and processing. The project reads integers from files stored in HDFS, checks if they are prime numbers, and finds the closest prime numbers.

## What is MapReduce?
MapReduce is a programming model used to process and generate large data sets. It consists of two main tasks:

 - Map: Processes input key/value pairs to generate a set of intermediate key/value pairs.
 - Reduce: Merges all intermediate values associated with the same intermediate key.

## Requirements

- Java 8
- Apache Hadoop 3.3.4
- Apache Maven 3.6.3 or later

## Setup Instructions

### Prerequisites

1. **Install Java 8**
    - Ensure that Java 8 is installed on your system.
    - Set the `JAVA_HOME` environment variable to point to your Java 8 installation.
    - Add `$JAVA_HOME/bin` to your `PATH`.

2. **Install Apache Hadoop**
    - Download and install Apache Hadoop 3.3.4.
    - Set up Hadoop by configuring `core-site.xml`, `hdfs-site.xml`, `mapred-site.xml`, and `yarn-site.xml`.

3. **Install Apache Maven**
    - Download and install Apache Maven 3.6.3 or later.
    - Add Maven to your `PATH`.

### Project Setup

1. **Clone the Repository**

    ```bash
    git clone https://github.com/yourusername/HadoopProject.git
    cd HadoopProject
    ```

2. **Update the Maven `pom.xml` File**

    Ensure that your `pom.xml` file is configured to use Java 8:

    ```xml
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    ```

3. **Create the `log4j.properties` File**

    Create a `log4j.properties` file in the `src/main/resources` directory:

    ```properties
    log4j.rootLogger=INFO, console
    log4j.appender.console=org.apache.log4j.ConsoleAppender
    log4j.appender.console.target=System.err
    log4j.appender.console.layout=org.apache.log4j.PatternLayout
    log4j.appender.console.layout.ConversionPattern=%d{ISO8601} %p %c: %m%n
    ```

4. **Compile and Package the Project**

    Run the following Maven commands to compile and package the project:

    ```bash
    mvn clean install
    mvn package
    ```

### Running the Hadoop Job

1. **Start Hadoop Services**

    Start HDFS and YARN services:

    ```bash
    start-dfs.cmd
    start-yarn.cmd
    ```

2. **Upload Input Files to HDFS**

    Create a directory in HDFS and upload your input files:

    ```bash
    hdfs dfs -mkdir -p /user/yourusername/input
    hdfs dfs -put C:/path/to/your/input/files/*.txt /user/yourusername/input/
    ```

3. **Run the MapReduce Job**

    Run the Hadoop MapReduce job using the packaged JAR file:

    ```bash
    hadoop jar target/HadoopProject-1.0-SNAPSHOT.jar edu.comp438.PrimeDriver /user/yourusername/input /user/yourusername/output
    ```

4. **Retrieve and Display Results**

    Retrieve the results from the output directory in HDFS:

    ```bash
    hdfs dfs -cat /user/yourusername/output/part-r-00000
    ```
    
    example :

![Capture](https://github.com/BaselAbuHamed/HadoopProject/assets/107325485/841cd006-9e10-409c-891b-cca54210a078)

    
- 100 false,101: The number 100 is not a prime number (false), and the next prime number after 100 is 101.
- 12 false,13: The number 12 is not a prime number (false), and the next prime number after 12 is 13.
- 23 true,29: The number 23 is a prime number (true), and the next prime number after 23 is 29.
- 35 false,37: The number 35 is not a prime number (false), and the next prime number after 35 is 37.
- 45 false,47: The number 45 is not a prime number (false), and the next prime number after 45 is 47.
- 5 true,7: The number 5 is a prime number (true), and the next prime number after 5 is 7.
- 67 true,71: The number 67 is a prime number (true), and the next prime number after 67 is 71.
- 89 true,97: The number 89 is a prime number (true), and the next prime number after 89 is 97.
- 9 false,11: The number 9 is not a prime number (false), and the next prime number after 9 is 11.

This output shows the integer processed, whether it is a prime number or not, and the next prime number.

## Problems Faced and Solutions Attempted
1. **Java Version Compatibility:**

    - Issue: Encountered UnsupportedClassVersionError due to incompatible Java versions.
    - Solution: Downgraded project to Java 8 by updating pom.xml and ensuring the environment used Java 8.
   
2. **SLF4J Binding Conflict:**

    - Issue: Multiple SLF4J bindings causing logging issues.
    - Solution: Excluded conflicting SLF4J dependencies and ensured only one binding was used.
   
3. **DataNode and NameNode Communication:**

    - Issue: Incompatible cluster IDs between DataNode and NameNode.
    - Solution: Reformatted the DataNode and NameNode directories and reinitialized Hadoop.
4. **File Not Found in HDFS:**

    - Issue: Errors in specifying correct file paths for input and output in HDFS.
    - Solution: Verified file paths using HDFS commands and ensured correct directory structure.


## Monitoring the Hadoop Cluster

### ResourceManager Web Interface

The ResourceManager web interface, accessible at [http://localhost:8088/cluster](http://localhost:8088/cluster), provides an overview of the cluster's resource usage and the status of running applications. This interface helps you monitor the applications submitted to the cluster, their progress, and resource allocation.

   ![Capture](https://github.com/BaselAbuHamed/HadoopProject/assets/107325485/ef6e638c-5f61-4524-b303-ca386314292e)


### NameNode Web Interface

The NameNode web interface, accessible at [http://localhost:9870/dfshealth.html#tab-overview](http://localhost:9870/dfshealth.html#tab-overview), provides detailed information about the HDFS health. It shows the status of DataNodes, storage capacity, and the overall health of the file system.

![Capture](https://github.com/BaselAbuHamed/HadoopProject/assets/107325485/ded764bc-f323-4296-8ea1-817af47d3916)


### Example of a Running MapReduce Job

Below is an example screenshot of the ResourceManager web interface showing the status of the Prime Number Finder MapReduce job:



This screenshot displays:

- **Cluster Metrics:** Information about the number of applications submitted, pending, running, and completed, as well as the resource usage.
- **Scheduler Metrics:** Details about the scheduling of resources.
- **Application Details:** Information about the MapReduce job, including the application ID, user, name, type, queue, priority, start time, finish time, state, and final status.

![Capture](https://github.com/BaselAbuHamed/HadoopProject/assets/107325485/a968ccba-e930-4c33-ac31-9b544a99fff3)




