# NewsPortal project
It's REST API project using Spring Framework, which executes CRUD operations.<br/> Only backend.
## Technologies
* Spring Framework, Hibernate
* Junit 5, Mockito, AssertJ
* Log4j2
* Java 11, Postgresql, Tomcat 9, Maven
## Deployment instructions
1. After launching IntelliJ Idea, click 'Get from Version Control',
   select 'Git', then paste the HTTPS address copied from the project on Github into the URL.
   (here is my address: https://github.com/AlmasMametanov/NewsPortal.git).
   Click 'clone'.
2. Run "SQL Shell (psql)" - PostgreSQL 15, copy/paste the scripts from /db/dbSchema.sql
   and execute the code.
3. Create hibernate.properties file in src/main/resources folder. 
And copy/paste fields from hibernate.properties.origin file into hibernate.properties file.
And fill values for hibernate.connection.username/password.
4. If you created a database from point 2 under a different name
   then in the hibernate.properties file in the src/main/resources folder,
   you need to correct the 'hibernate.connection.url' under the new name.
5. Connect the Apache Tomcat server to start the project.
   Click on the top 'Run' -> 'Edit Configurations'. Next, in the window that opens, in the upper left corner,
   click '+', select 'Tomcat Server' -> 'Local'. In the window that appears in the 'Application Server',
   select 'Tomcat 9.0.63', then specify 'JRE 11.0.15.1'. Next select 'Deployment', press '+', press 'Artifact',
   and select 'NewsPortal:war exploded'. Delete the 'Application context' line. Then click 'Apply' and 'OK'.
6. You can Run the project.
