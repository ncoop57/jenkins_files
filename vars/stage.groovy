def createEnvironment(repo, path, stage)
{

  dir("${path}")
  {

    if (stage.equals("staging"))
    {

      sh "bash push.sh $REPO /cdep/repos"

    }
    else 
    {

      def image = docker.build("${stage}");
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
