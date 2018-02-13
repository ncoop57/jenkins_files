def createEnvironment(repo, url, path, stage)
{

  dir("${path}")
  {

    if (stage.equals("staging"))
    {

      sh "docker exec -i staging bash /var/www/html/staging.sh ${url} /var/www/html/${repo}"

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
