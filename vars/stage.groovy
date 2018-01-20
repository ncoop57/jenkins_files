def createEnvironment(repo, path, stage)
{

  dir("${path}")
  {

    def image = docker.build("${stage}");
    if (stage.equals("staging"))
    {

      sh "bash push.sh $REPO /cdep/repos"

    }
    else 
    {

      withEnv(["REPO=${repo}"])
      {

        image.inside("--network=dockercompose_default -v /cdep:/cdep")
        {

          sh "bash build.sh $REPO"

        }

      }

    }

  }

}
