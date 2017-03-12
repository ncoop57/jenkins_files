package edu.uwf

class UnitStage implements Serializable
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

            steps.sh "bash ${path}/localtest.sh"
            steps.sh "docker exec jpipeline bash -c 'cd /maven/MediumFX/; mvn -Dtest=* test'"

        }

    }

}
