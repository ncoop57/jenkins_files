def createEnvironment(repo, path, stage)
{

    dir("${path}")
    {

        def image = docker.build("${stage}")
	if (stage.equals("integration"))
	{

	    image.inside("--link database:db -v /cdep/repos/${repo}:/cdep")
	    {

		sh 'bash build.sh'

	    }

	}
	else 
	{
	    sh "ls -l"
	    withEnv(["REPO=${repo}"]) {
		image.inside("-v /cdep:/cdep")
		{

		    print "inside node container"
		    sh "ls -l"
		    sh "bash build.sh $REPO"

		}
	    }
	}

    }

}
