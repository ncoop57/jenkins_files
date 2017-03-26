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
def configParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json)
}

// Parsing the push notification to get the repo's url
@NonCPS
def urlParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).repository.ssh_url
}

// Parsing the push notification to get the repo's branch
@NonCPS
def branchParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).ref
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

                def integration = new edu.uwf.IntegrationStage()
                integration.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/integration")

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
                merge.createEnvironment("/home/ec2-user/workspace/DevOps/tests/staging/stage/${language}_stage/merging", repo, url, branch, language)

            }

        }

    }

}

node('docker_box')
{

    def config

    // Grabbing the repo's url
    def url = urlParse(payload)

    // Parsing the url to grab the repo's name
    def repo = url.tokenize('/')[1].tokenize('.')[0].toLowerCase()

    // Grabs the branch that was updated
    def branch = branchParse(payload).tokenize('/')[2].trim()

    dir('/home/ec2-user/workspace/DevOps')
    {

        def checkout = new CheckoutStage(steps)

        checkout.updateTesterRepo()
        checkout.checkoutRepo(url, repo, branch)
        echo 'Updated the tester repo'

    }

    stage('Parsing')
    {
        dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
        {

            config = configParse(sh (script: 'cat config.json', returnStdout: true).trim())

        }
    }

    try
    {

        makeStages(config.stages, repo, url, branch, config.language)

    }
    catch(e)
    {

        currentBuild.result = "FAILURE"

    }

    def cleanupStage = new CleanupStage(steps)
    cleanupStage.cleanup(repo)

}
