def createEnvironment(repo, path, stage)
{

    stage ("Testing")
    {
        dir("${path}")
        {

            def image = docker.build("${stage}")
            image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/cdep")
            {

                sh 'bash build.sh'

            }

        }

    }

}
