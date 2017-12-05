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

            steps.sh "git clone ${url} /cdep/repos/${repo}"
            steps.dir("/cdep/repos/${repo}")
            {

                steps.sh "git checkout ${branch}"

            }

        }

    }

}
