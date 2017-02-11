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

            sh "git clone ${url} ${repo}"
            sh "git checkout ${branch}"

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
