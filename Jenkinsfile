@Library('shared_libraries') import edu.uwf.*
import groovy.json.JsonSlurper

// parse the stages to do
@NonCPS
def stageParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).stages
}

// parse the language being used
@NonCPS
def languageParse(def json)
{
    new groovy.json.JsonSlurper().parseText(json).language
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

            try
            {

                steps.stage('Build')
                {

                    if (currentBuild.result != "FAILURE")
                    {

                        stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/build", "build")

                    }
                    else echo "Skipping due to failure"

                }

            }
            catch(e)
            {

                currentBuild.result = "FAILURE"

            }

        }
        else if (stages[i].equals("static"))
        {

            try
            {

                steps.stage('Static Analysis')
                {

                    if (currentBuild.result != "FAILURE")
                    {

                        stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/static", "static")

                    }
                    else echo "Skipping due to failure"

                }

            }
            catch(e)
            {

                currentBuild.result = "UNSTABLE"

            }

        }
        else if (stages[i].equals("unit"))
        {

            try
            {

                steps.stage('Unit Testing')
                {

                    if (currentBuild.result != "FAILURE")
                    {

                        stage.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/unit", "unit")

                    }
                    else echo "Skipping due to failure"

                }

            }
            catch(e)
            {

                currentBuild.result = "FAILURE"

            }

        }
        else if (stages[i].equals("integration"))
        {

            try
            {

                steps.stage('Integration Testing')
                {

                    if (currentBuild.result != "FAILURE")
                    {

                        def integration = new edu.uwf.IntegrationStage()
                        integration.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/${language}/integration")

                    }
                    else echo "Skipping due to failure"

                }

            }
            catch(e)
            {

                currentBuild.result = "FAILURE"

            }

        }
        else if (stages[i].equals("staging"))
        {

            try
            {

                steps.stage ("Staging")
                {

                    if (currentBuild.result != "FAILURE")
                    {

                        def staging = new StagingStage(steps)
                        staging.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/staging/stage/${language}_stage")

                    }
                    else echo "Skipping due to failure"

                }

            }
            catch(e)
            {

                currentBuild.result = "FAILURE"

            }

            try
            {

                steps.stage('Merging')
                {

                    if (currentBuild.result != "FAILURE")
                    {

                        def merge = new edu.uwf.MergingStage()
                        merge.createEnvironment("/home/ec2-user/workspace/DevOps/tests/staging/stage/${language}_stage/merging", repo, url, branch, language)

                    }
                    else echo "Skipping due to failure"

                }

            }
            catch(e)
            {

                currentBuild.result = "FAILURE"

            }

        }

    }

}

node('docker_box')
{

    def config
    def language
    def stages

    // Grabbing the repo's url
    def url = urlParse(payload)

    // Parsing the url to grab the repo's name
    def repo = url.tokenize('/')[1].tokenize('.')[0].toLowerCase()

    // Grabs the branch that was updated
    def branch = branchParse(payload).tokenize('/')[2].trim()

    // Setting the name of the build to the something unique to the repo and branch
    currentBuild.displayName = "${repo}: ${branch}"

    if(branch != "master")
    {

        dir('/home/ec2-user/workspace/DevOps')
        {

            try
            {

                def checkout = new CheckoutStage(steps)

                checkout.updateTesterRepo()
                checkout.checkoutRepo(url, repo, branch)
                echo 'Updated the tester repo'

            }
            catch(e)
            {

                currentBuild.result = "FAILURE"

            }

        }

        try
        {

            dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
            {

                config = sh (script: 'cat config.json', returnStdout: true).trim()
                stages = stageParse(config)
                language = languageParse(config)

            }

            makeStages(stages, repo, url, branch, language)

        }
        catch(e)
        {

            currentBuild.result = "FAILURE"

        }

        def cleanupStage = new CleanupStage(steps)
        cleanupStage.cleanup(repo)

    }
    else echo "Cannot test master branch"

}
