@Library('shared_libraries') import edu.uwf.*
import groovy.json.JsonSlurper

// parse the stages to do
@NonCPS
def stageParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).stages
}

def makeStages(def stages, def repo)
{

    for (int i = 0; i < stages.size(); i++)
    {

        if (stages[i].equals("build"))
        {

            def staticAnalysis = new StaticStage(steps)
            staticAnalysis.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget")

        }
        else if (stages[i].equals("static"))
        {

            try
            {
                def staticAnalysis = new StaticStage(steps)
                staticAnalysis.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget")

            }
            catch(e)
            {

                currentBuild.result = "UNSTABLE"

            }

        }
        else if (stages[i].equals("unit"))
        {

            def unitTest = new UnitStage(steps)
            unitTest.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/localphpunit")

        }
        else if (stages[i].equals("integration"))
        {

            def integrationTest = new IntegrationStage(steps)
            integrationTest.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/phpunit")

        }
        else if (stages[i].equals("staging"))
        {

            def staticAnalysis = new StaticStage(steps)
            staticAnalysis.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget")

        }
        else if (stages[i].equals("merging"))
        {

            def staticAnalysis = new StaticStage(steps)
            staticAnalysis.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget")

        }

    }

}

node('docker_box')
{

    def text
    def url = "git@github.com:UWF-HMCSE-CS/SEMDEVOPS011.git"
    def repo = "semdevops011"
    def branch = "feature"

    dir("/home/ec2-user/workspace")
    {

        text = stageParse(sh (script: 'cat test.json', returnStdout: true).trim())

    }

    dir('/home/ec2-user/workspace/DevOps')
    {

        def checkout = new CheckoutStage(steps)

        checkout.updateTesterRepo()
        checkout.checkoutRepo(url, repo, branch)
        echo 'Updated the tester repo'

    }

    makeStages(text, repo)

    def cleanupStage = new CleanupStage(steps)
    cleanupStage.cleanup(repo)

    echo text[0]

}
