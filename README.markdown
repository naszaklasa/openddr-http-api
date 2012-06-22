This project is a HTTP server that allows querying OpenDDR API/database in more efficient manner.
HTTP server by default runs at port 6522,
to query it use:

http://localhost:6522/?ua=Mozilla/5.0%20(iPhone;%20U;%20CPU%20iPhone%20OS%204_2_1%20like%20Mac%20OS%20X;%20pl-pl)%20AppleWebKit/533.17.9%20(KHTML,%20like%20Gecko)%20Version/5.0.2%20Mobile/8C148%20Safari/6533.18.5

example result is:
`{"model":"iPhone","vendor":"Apple","displayWidth":"320","displayHeight":"480"}` 

#USAGE MANUAL:
1. clone this project
2. change paths in `oddr.properties` file to match your configuration
3. modify properties in `Configuration` class
4. `mvn package` it
5. run `java -cp target/openddr-http.jar pl.nk.warehouse.openddr.OpenDDRServer`

#DEVELOPMENT MANUAL:
example pom.xml can be found here: https://gist.github.com/1760081

1. download OpenDDR-1.0.0.2.zip from http://openddr.org (please use latest version, and change it below to match)

2. unpack it

3. add DDR-Simple-API.jar from lib directory to maven

`w@w:~/OpenDDR-1.0.0.2/lib$ mvn install:install-file -Dfile=DDR-Simple-API.jar -DgroupId=org.openddr -DartifactId=simple-api -Dversion=1.0.0.2 -Dpackaging=jar`

you should see something like this:

		[INFO] Scanning for projects...
		[INFO] Searching repository for plugin with prefix: 'install'.
		[INFO] org.apache.maven.plugins: checking for updates from central
		[INFO] org.apache.maven.plugins: checking for updates from snapshots
		[INFO] org.codehaus.mojo: checking for updates from central
		[INFO] org.codehaus.mojo: checking for updates from snapshots
		[INFO] ------------------------------------------------------------------------
		[INFO] Building Maven Default Project
		[INFO]    task-segment: [install:install-file] (aggregator-style)
		[INFO] ------------------------------------------------------------------------
		[INFO] [install:install-file {execution: default-cli}]
		[INFO] Installing /home/w/OpenDDR-1.0.0.2/lib/DDR-Simple-API.jar to /home/w/.m2/repository/org/openddr/simple-api/1.0.0.2/simple-api-1.0.0.2.jar
		[INFO] ------------------------------------------------------------------------
		[INFO] BUILD SUCCESSFUL
		[INFO] ------------------------------------------------------------------------
		[INFO] Total time: 6 seconds
		[INFO] Finished at: Fri Feb 03 14:03:52 CET 2012
		[INFO] Final Memory: 4M/72M
		[INFO] ------------------------------------------------------------------------

4. add maven dependency:

		<dependency>
			<groupId>org.openddr</groupId>
			<artifactId>simple-api</artifactId>
			<version>1.0.0.2</version>
		</dependency>

5. fix one error on in VocabularyHolder class (change import from `import org.apache.commons.lang.ArrayUtils` to `import org.apache.commons.lang3.ArrayUtils`)

6. note that resource files (xml files with attributes) are not included with this release, they are located in resources directory in previously unpacked archive

