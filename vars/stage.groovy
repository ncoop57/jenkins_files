def createEnvironment(repo, path, stage)
{

  dir("${path}")
  {

    def image = docker.build("${stage}");
    if (stage.equals("integration"))
    {

      withEnv(["REPO=${repo}"])
      {

        image.inside("--link database:db -v /cdep/repos/${repo}:/cdep")
        {

          sh "bash build.sh $REPO"

        }

      }

    }
    else 
    {

      withEnv(["REPO=${repo}"])
      {

        image.inside("-v /cdep:/cdep")
        {

          sh "bash build.sh $REPO"

        }

      }

    }

  }

}
