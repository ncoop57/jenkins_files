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
