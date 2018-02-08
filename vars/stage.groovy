def createEnvironment(repo, path, stage)
{

  dir("${path}")
  {

    if (stage.equals("staging"))
    {

      sh "docker exec -d staging git clone https://github.com/Keenal/hello-world.git"

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
