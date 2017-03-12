package edu.uwf

def createEnvironment(steps, repo, path)
{

    steps.stage ("Unit Testing")
    {

        steps.dir("${path}")
        {

            var image = docker.build("junit")
            image.inside
            {

                sh 'echo pwd'

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
