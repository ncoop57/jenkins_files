package edu.uwf

class BuildStage implements Serializable
{
    def steps

    BuildStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(script, repo, path)
    {

        steps.stage ("Build")
        {

            def maven
            steps.dir("${path}")
        	{

                // Building the docker image from the Dockerfile
                //maven = script.docker.build("jpipeline")
                steps.sh "docker build -t jpipeline ${path}"
                steps.sh "docker run -t -d -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --name \"jpipeline\" jpipeline"
                steps.sh "docker exec jpipeline bash -c 'cd /pipeline/; mvn archetype:generate -B -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1 -DgroupId=com.company -DartifactId=project -Dversion=1.0-SNAPSHOT -Dpackage=com.company.project; mvn test"
                // Running the docker image and creating a container
                /*maven.inside
        		{

                    // Generating the maven project with sample app
                    steps.sh 'mvn archetype:generate -B -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1 -DgroupId=com.company -DartifactId=project -Dversion=1.0-SNAPSHOT -Dpackage=com.company.project'

        			//stage 'Unit Test'
                    // Performing JUnit test on the sample app
        			steps.sh '(cd ./project; mvn test)'

        		}*/

            }
            //steps.sh "docker build -t ${repo} ${path}"
            //steps.sh "docker run --link database:db -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --rm ${repo}"

        }

    }

}
