# Hadoop Prime Number Finder

## Overview

Apache Hadoop is a platform that facilitates using a network of many computers to solve problems involving massive amounts of data and computation. It provides a software framework for distributed storage and processing of big data using the MapReduce programming model.

This project modifies a previous project to use Hadoop for distributed storage and processing. The project reads integers from files stored in HDFS, checks if they are prime numbers, and finds the closest prime numbers.

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

