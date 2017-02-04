node('docker_box')
{
    stage 'Build'

    def maven

    dir("/home/ec2-user/workspace/maven")
	{

        // Building the docker image from the Dockerfile
        maven = docker.build("jpipeline")

        // Running the docker image and creating a container
        maven.inside
		{

            // Generating the maven project with sample app
            sh 'mvn archetype:generate -B -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1 -DgroupId=com.company -DartifactId=project -Dversion=1.0-SNAPSHOT -Dpackage=com.company.project'

			stage 'Unit Test'
            // Performing JUnit test on the sample app
			sh '(cd ./project; mvn test)'

		}

}
