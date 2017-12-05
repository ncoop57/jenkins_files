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

	if (currentBuild.result == "FAILURE")
	    break;

	try
	{

	    steps.stage(stages[i])
	    {

		stage.createEnvironment(repo, "/cdep/tests/${language}/${stages[i]}", stages[i])

	    }

	}
	catch(e)
	{

	    if (stages[i].equals("static"))
	    {
                currentBuild.result = "UNSTABLE"
	    }
	    else currentBuild.result = "FAILURE";

	}

    }

}

node()
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

        try
        {

	    def checkout = new CheckoutStage(steps)
	    checkout.checkoutRepo(url, repo, branch)

            dir("/cdep/repos/${repo}")
            {


                config = sh (script: 'cat config.json', returnStdout: true).trim()
                stages = stageParse(config)
                language = languageParse(config)

            }

	    sh "ls -l cdep"
            makeStages(stages, repo, url, branch, language)

        }
        catch(e)
        {

            currentBuild.result = "FAILURE"

        }

	sh 'rm -fr /cdep/repos/${repo}'
	sh 'rm -fr /cdep/repos/${repo}\\@tmp'

    }
    else echo "Cannot test master branch"

}
