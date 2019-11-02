The following documentation describes the project within this folder and how to use it effectively.

Use the following  URLs to access the website: 	35.242.137.2:8080/workoutTracker-1.0/
						or
						35.242.137.2:8080?workoutTracker:8080/frontpage

The project is currently running on a google cloud virtual machine and being automatically updated via Jenkins whenever there is a push to github.
A trello board was used to keep track of tasks and progress of the project.


On the homepage, there is an option to either create a user or login to a current user. The homepage is accessable from any other page within the website
but it will log the user out. If you create a user, you can go straight to the login page from there. Please note that you cannot enter blank entries in 
both the boxes. Login requires a first name and a last name that are identical to a user already created.


Once in the users account, there are opportunities to observe the users data, hide it again, update the account, or even delete the account. Account 
deletion is not complete until confirmed by the user. If update is selected, the user will be taken to a page similar to the create/login pages. Again, in
the update page, a blank name cannot be entered. If a correct change is made, the username will be updated and then the user will be taken back to the home
page and be required to login again. A future addition to the user's account page, there will be a chance to view workouts, adding and removing them.

The users are stored and viewed by connecting to a h2database using Java Persistence API and then displayed to the web pages using javascript, html and css.

The test coverage for this project in Eclipse measured at 82.8%. Selenium tests were prioritised with some junit testing as well. The API was checked by 
using postman in order to observe the POST, GET, PUT and DELETE commands (CRUD) in action. The selenium tests show that the CRUD is working effectively on
the front end of the project.


_________________________________________________________________________________________________________________________________________________________



If anyone is trying to recreate this project, or one similar, there are a few steps required beforehand in order for the server to work correctly.

Deploying the project was done using java-8 and apache-maven-3.6.1. The Java Persistence API was setup using eclipse. The projects requires setting up a new
Maven project primarily for testing and various xml documents that can be found in the document.

The relational database management system used was h2. In order to connect to the h2 database engine, the h2 jar file must be downloaded and ran. The jar 
file used was h2-1.4.197.jar. To run this file, simply open a command prompt in the directory in which the jar is located and type the command: 
Java -jar h2-1.4.197.jar. The database will then be able to be located at localhost:8082. By default the password will either be 'sa' or nothing by default.
The Saved Settings on h2 should be the 'Generic H2 (Server)' in order for data to exist permanently on the database. A hibernate dependency is also required
in the pom xml, which enables Java to connect to H2.

Wildfly is required to host the website, either locally or online. Wildfly 10.1.0.Final was used mostly, but version 16 can also work. Once the java API is
completed, the war file can be created in eclipse by running a maven clean install. This war file is then transferred to the wildfly deployments folder in
the standalone directory. The standalone.xml for wildfly can be found in configurations and needs an extra datasource added:

	<datasource jta="true" jndi-name="java:jboss/datasources/MyApplicationDS" pool-name="MyApplicationDS" enabled="true" use-ccm="true">
                    <connection-url>jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
                    <driver-class>org.h2.Driver</driver-class>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                    </security>
                    <validation>
                        <background-validation>false</background-validation>
                    </validation>
	</datasource>

Afterwards, the war file is ready to be deployed. Run the standalone.bat within the bin directory in wildfly, and the build will automatically deploy or
fail. If it fails, errors can be checked within the standalone.bat command prompt.

The CRUD functionality can be tested using Postman. When posting, make sure all text is in a JavaScript Object Notation (JSON) format as the front end of
the project is coded in JavaScript.

Wildfly is available at localhost:8080, and the website will be accessable at localhost:8080/<project name>/, provided there are html files that correspond
to paths. 

These same steps can be setup on a cloud virtual machine in order to have the website run online. This particular project was made using the Google Cloud
Platform (GCP), using Jenkins to apply continuous inegration. Jenkins can be setup so that Github can notify it when there is a change in the project, which
then changes the website online automatically.

Other important things required for the project to work is a persistence.xml and a beans.xml. These are important because objects need to be made into
entities and then persisted to the database. Persistence units and entity managers are used to do this within Eclipse.

Html, JavaScript and CSS files were all created and edited using Visual Studio Code (VSCode).