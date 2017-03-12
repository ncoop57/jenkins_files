package edu.uwf

def createEnvironment(steps, repo, path)
{

    steps.stage ("Unit Testing")
    {

        steps.dir("${path}")
        {

            def image = docker.build("junit")
            image.withRun("-v /home/ec2-user/workspace/jenkins_pipeline/medium:/maven")
            {

                steps.sh 'ls'

            }

        }
      //  steps.sh "bash ${path}/localtest.sh"
      //  steps.sh "docker exec junit bash -c 'cd /maven/MediumFX/; mvn -Dtest=* test'"
      //  steps.sh "bash ${path}/../../../../../cleanup.sh"

    }

}

/*class UnitStage implements Serializable
{
    def steps

    UnitStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo, path)
    {

        steps.stage ("Unit Testing")
        {

            var image = docker.build("${path}")
          //  steps.sh "bash ${path}/localtest.sh"
          //  steps.sh "docker exec junit bash -c 'cd /maven/MediumFX/; mvn -Dtest=* test'"
          //  steps.sh "bash ${path}/../../../../../cleanup.sh"

        }

    }

}*/
