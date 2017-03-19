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

            steps.stage('Build')
            {

                stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/build", "build")

            }

        }
        else if (stages[i].equals("static"))
        {

            try
            {

                steps.stage('Static Analysis')
                {

                    stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/static", "static")

                }

            }
            catch(e)
            {

                currentBuild.result = "UNSTABLE"

            }

        }
        else if (stages[i].equals("unit"))
        {

            steps.stage('Unit Testing')
            {

                stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/unit", "unit")

            }

        }
        else if (stages[i].equals("integration"))
        {


            steps.stage('Integration Testing')
            {

                stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/integration", "integration")

            }

        }
        else if (stages[i].equals("staging"))
        {

            def staging = new StagingStage(steps)
            staging.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/staging/stage/${language}_stage")

        }
        else if (stages[i].equals("merging"))
        {

            steps.stage('Merging')
            {

                def merge = new edu.uwf.MergingStage()
                merge.createEnvironment("/home/ec2-user/workspace/DevOps/tests/staging/stage/${language}_stage/merging", repo, url, branch)

            }

        }

    }

}

node('docker_box')
{

    def text
    def url = "git@github.com:UWF-HMCSE-CS/SEMDEVOPS001.git"
    def repo = "semdev001001"
    def branch = "feature"

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
