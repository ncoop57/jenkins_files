def createEnvironment(repo, url, branch, path, stage)
{

  // Perform all the following commands in the given path
  dir("${path}")
  {

    // Check the stage type
    if (stage.equals("staging"))
    {

      // Perform the action required for the staging environment
      dir("/cdep/repos/$repo")
      {
        sh "mv target/*.jar target/application.jar"
        sh "git add -A"
        sh "git commit -m \"Staging build\""
        sh "git push"
        sh "git checkout master"
        sh "git pull origin master"
        sh "git merge $branch"
        sh "git push origin master"
        sh "docker exec -i staging bash /var/www/html/staging.sh ${url} /var/www/html/${repo} $branch"
      }

    }
    else 
    {

      // Build the docker image for the specific stage using the Dockerfile in
      // the current directory
      def image = docker.build("${stage}");
      withEnv(["REPO=${repo}"]) // Pass the repo name as an environment variable
      {

        // Run the docker container with the specified default network and
        // mounting folder
        image.inside("--network=dockercompose_default -v /cdep:/cdep")
        {

          // Execute the specific build script for the given stage on the repo
          sh "bash build.sh $REPO"

        }

      }

    }

  }

}
