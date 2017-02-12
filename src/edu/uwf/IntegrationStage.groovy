package edu.uwf

class IntegrationStage implements Serializable
{
    def steps

    IntegrationStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo, path)
    {

        steps.stage ("Integration Testing")
        {

            steps.sh "docker build -t ${repo} ${path}"
            steps.sh "docker run --link database:db -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --rm ${repo}"

        }

    }

}
