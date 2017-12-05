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

	    image.inside("-v /cdep/repos/${repo}:/cdep")
	    {

		print "inside node container"
		sh("bash build.sh")

	    }

	}

    }

}
