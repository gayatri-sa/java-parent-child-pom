# java-parent-child-pom
Java parent child POM example with Maven Profiles

+ Building the parent project will automatically build all the child projects too; you do not need to explicitly build them.
+ The child projects will inherit all the properties of the parent. So, any values set in the parent pom will be valid & applicable for all the child poms too.

# Steps
## 1. Create parent project and change the <packaging> to "pom" in the pom.xml.
```
mvn archetype:generate -DgroupId=org.nivi -DartifactId=parent-project -DinteractiveMode=false
```
## 2. Enter the parent project folder and create the child projects.
```
cd parent-project
mvn archetype:generate -DgroupId=org.nivi  -DartifactId=core -DinteractiveMode=false
mvn archetype:generate -DgroupId=org.nivi  -DartifactId=service -DinteractiveMode=false
```
## 3. Build the parent project (default profile is "dev")
```
mvn clean package
```
## 4. Build the parent project with new profile "prod"
```
mvn clean package -P prod
```
## 5. Execute any child project war 
Here I am executing the WAR of the core/ child project
```
java -cp target/core-1.0-SNAPSHOT.jar org.nivi.App
```


# Docker Commands
## 1. Create parent project and change the <packaging> to "pom" in the pom.xml.
```
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v "$PWD/.m2":/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 mvn archetype:generate -DgroupId=org.nivi -DartifactId=parent-project -DinteractiveMode=false
```
## 2. Enter the parent project folder and create the child projects.
```
cd parent-project
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v "$PWD/../.m2":/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 mvn archetype:generate -DgroupId=org.nivi  -DartifactId=core -DinteractiveMode=false
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v "$PWD/../.m2":/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 mvn archetype:generate -DgroupId=org.nivi  -DartifactId=service -DinteractiveMode=false
cd ../
```
## 3. Build the parent project (default profile is "dev")
```
docker run -it --rm --name my-maven-project -v "$PWD/parent-project":/usr/src/mymaven/parent-project -v "$PWD/.m2":/root/.m2 -w /usr/src/mymaven/parent-project maven:3.3-jdk-8 mvn clean package
```
## 4. Build the parent project with new profile "prod"
```
docker run -it --rm --name my-maven-project -v "$PWD/parent-project":/usr/src/mymaven/parent-project -v "$PWD/.m2":/root/.m2 -w /usr/src/mymaven/parent-project maven:3.3-jdk-8 mvn clean package -P prod
```
## 5. Execute any child project war 
Here I am executing the WAR of the core/ child project
```
docker run -it --rm --name my-maven-project -v "$PWD/core":/usr/src/mymaven -v "$PWD/../.m2":/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 java -cp target/core-1.0-SNAPSHOT.jar org.nivi.App
```
# All Thanks To
https://www.baeldung.com/maven-multi-module

https://mkyong.com/java/java-properties-file-examples/
