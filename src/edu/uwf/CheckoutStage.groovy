package edu.uwf

class CheckoutStage implements Serializable
{
    def steps

    CheckoutStage(steps)
    {
        this.steps = steps
    }

    def updateTesterRepo()
    {

        try
        {
            steps.sh "git pull"
        }
        catch (e)
        {
            currentBuild.result = "FAILURE"
        }

    }

    def checkoutRepo(url, repo, branch)
    {

        try
        {

            steps.sh "git clone ${url} /home/ec2-user/workspace/jenkins_pipeline/${repo}"
            steps.dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
            {
                steps.sh "git checkout ${branch}"
            }

        }
        catch (e)
        {
            steps.currentBuild.result = "FAILURE"
        }

    }

    def thing(args)
    {
        steps.echo "${args}"
    }

}
