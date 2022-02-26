<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body><!-- 
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                              http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>TeluguCineAndTVOutDoorUnitTechniciansUnionApplication</groupId>
    <artifactId>TeluguCineAndTVOutDoorUnitTechniciansUnionApplication</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.1.RELEASE</version>
    </parent>

    <!-- <organization>
        <name>ESpark</name>
        <url>http://adarshkumarsingh83.blogspot.in/</url>
    </organization>
     -->

  <!-- <licenses>
        <license>
            <name>ESpark</name>
            <url>http://adarshkumarsingh83.blogspot.in/licenses/LICENSE-2.0.txt</url>
            <distribution>repo</distribution>
        </license>
    </licenses>
    
    
    
    <developers>
        <developer>
            <id>adarshkumarsingh83</id>
            <name>Adarsh Kumar</name>
            <email>adarshkumarsingh83@gmail.com</email>
            <roles>
                <role>project architect</role>
            </roles>
        </developer>
    </developers>

    <repositories>
        <repository>
            <id>maven2-repository.java.net</id>
            <name>Java.net Repository for Maven</name>
            <url>http://download.java.net/maven/2/</url>
        </repository>

        <repository>
            <id>JBoss repository</id>
            <url>http://repository.jboss.org/nexus/content/groups/public/</url>
        </repository>

        <repository>
            <id>java.net</id>
            <url>https://maven.java.net/content/repositories/public/</url>
        </repository>

    </repositories>
    
     -->  
    

    <properties>
        <project.name>TeluguCineAndTVOutDoorUnitTechniciansUnionApplication</project.name>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.7</java.version>
        <!-- Hibernate / JPA -->
                <!--  <hibernate.version>4.3.5.Final</hibernate.version>
         <hibernate.version>3.6.9.Final</hibernate.version>-->        
    </properties>

    <dependencies>
        <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-web</artifactId>
        <version>4.0.1.RELEASE</version>
    </dependency>
    
    <!-- Hibernate -->
                <dependency>
                        <groupId>org.hibernate</groupId>
                        <artifactId>hibernate-entitymanager</artifactId>
                        <version>${hibernate.version}</version>
                </dependency>
                <dependency>
                        <groupId>org.hibernate</groupId>
                        <artifactId>hibernate-core</artifactId>
                        <version>${hibernate.version}</version>
                </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.3.1</version>
  </dependency>

        <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-solr</artifactId>
        </dependency>
        <dependency>
	<groupId>cglib</groupId>
	<artifactId>cglib</artifactId>
	<version>2.2.2</version>
</dependency>
    </dependencies>

<build>
    <plugins>
     
         <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
      <version>1.5.6.RELEASE</version>
      <configuration>
         <profiles>
            <profile>foo</profile>
            <profile>bar</profile>
          </profiles>
      
        <mainClass>${start-class}</mainClass>
        <layout>ZIP</layout>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
       <addResources>true</addResources>
      </configuration>
      <executions>
        <execution>
          <goals>
            <goal>repackage</goal>
          </goals>
        </execution>
      </executions>
      
    </plugin>
    </plugins>

</build>
</project> -->
</body>
</html>