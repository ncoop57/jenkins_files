package edu.uwf

class StaticStage implements Serializable
{
    def steps

    StaticStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo, path)
    {

        steps.stage ("Static Analysis")
        {
        
            steps.sh "docker build -t ${repo} ${path}"
            steps.sh "docker run -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --rm ${repo}"

        }

    }

}
