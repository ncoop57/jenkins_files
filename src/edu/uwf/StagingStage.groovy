package edu.uwf

class StagingStage implements Serializable
{
    def steps

    StagingStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo, path)
    {

        steps.stage ("Staging")
        {

            steps.sh "bash ${path}/build.sh"

        }

    }

}
