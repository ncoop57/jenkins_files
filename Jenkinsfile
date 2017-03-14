@Library('shared_libraries') import edu.uwf.*
import groovy.json.JsonSlurper

// parse the stages to do
@NonCPS
def stageParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).stages
}

// parse the language to do
@NonCPS
def languageParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).language
}

def makeStages(stages, repo, url, branch, language)
{

    for (int i = 0; i < stages.size(); i++)
    {

        if (stages[i].equals("build"))
        {

            //def build = new BuildStage(steps)
            stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/build", "build")

        }
        else if (stages[i].equals("static"))
        {

            try
            {
                def staticAnalysis = new edu.uwf.StaticStage()
                staticAnalysis.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/static")

            }
            catch(e)
            {

                currentBuild.result = "UNSTABLE"

            }

        }
        else if (stages[i].equals("unit"))
        {

            def unitTest = new edu.uwf.UnitStage()
            unitTest.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/unit")

        }
        else if (stages[i].equals("integration"))
        {

            def integrationTest = new edu.uwf.IntegrationStage()
            integrationTest.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/integration")

        }
        else if (stages[i].equals("staging"))
        {

            def staging = new StagingStage(steps)
            staging.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/staging")

        }
        else if (stages[i].equals("merging"))
        {

            def merge = new MergingStage(steps)
            merge.createEnvironment(repo, url, branch)

        }

    }

}

node('docker_box')
{

    def text
    def url = "git@github.com:BhavyanshM/Medium.git"
    def repo = "medium"
    def branch = "master"

    dir("/home/ec2-user/workspace")
    {

        text = stageParse(sh (script: 'cat test.json', returnStdout: true).trim())
        language = languageParse(sh (script: 'cat test.json', returnStdout: true).trim())

    }

    dir('/home/ec2-user/workspace/DevOps')
    {

        def checkout = new CheckoutStage(steps)

        checkout.updateTesterRepo()
        checkout.checkoutRepo(url, repo, branch)
        echo 'Updated the tester repo'

    }

    try
    {
        makeStages(text, repo, url, branch, language)
    }
    catch(e)
    {

        currentBuild.result = "FAILURE"

    }

    def cleanupStage = new CleanupStage(steps)
    cleanupStage.cleanup(repo)

    echo text[0]

}
