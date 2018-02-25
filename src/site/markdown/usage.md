#  Beanshell Touch Maven Plugin


The following configuration shows how to use the Beanshell Touch Maven Plugin in a pom:

	<project>
	  [...]
	  <build>
	    [...]
	    <plugins>
	      <plugin>
	        <groupId>feb25jen</groupId>
	        <artifactId>webapps</artifactId>
	        <version>1.0-SNAPSHOT</version>
			<executions>
				<execution>
					<id>touch</id>
					<phase>validate</phase
					<goals>
						<goal>touch</goal>
					</goals>
				</execution>
			</executions>
	        <configuration>
	          <file>touch.txt</file>
	        </configuration>
					
	      </plugin>
	      [...]
	    </plugins>
	    [...]
	  </build>
	  [...]
	</project>

Here is an example of how to use the Beanshell Touch Maven Plugin from the command line:

	mvn feb25jen:webapps:1.0-SNAPSHOT:touch -Dfile=touch.txt
