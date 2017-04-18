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

        steps.sh "bash ${path}/bash/push.sh ${repo}"

    }

}
