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

            steps.sh "docker build -t ${repo} ${path}"
            steps.sh "docker run -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --rm ${repo}"

        }

    }

}
