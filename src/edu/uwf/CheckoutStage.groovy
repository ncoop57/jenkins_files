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

        steps.stage ("Checkout")
        {

            steps.sh "git clone ${url} /cdep/repos/${repo}"
            steps.dir("/cdep/repos/${repo}")
            {

                steps.sh "git checkout ${branch}"

            }

        }

    }

    def thing(args)
    {
        steps.echo "${args}"
    }

}
