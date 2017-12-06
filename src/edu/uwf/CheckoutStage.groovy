package edu.uwf

class CheckoutStage implements Serializable
{
    def steps

    CheckoutStage(steps)
    {
        this.steps = steps
    }

    def checkoutRepo(url, repo, branch)
    {

        steps.stage ("Checkout")
        {

	    steps.dir("/cdep/repos/")
	    {
		steps.sh "git clone ${url} ${repo}"
	    }
            steps.dir("/cdep/repos/${repo}")
            {
                steps.sh "git checkout ${branch}"
            }

        }

    }

}
