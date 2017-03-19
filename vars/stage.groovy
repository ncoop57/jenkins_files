def createEnvironment(repo, path, stage)
{

    dir("${path}")
    {

        def image = docker.build("${stage}")
        image.inside("--link database:db -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/cdep")
        {

            sh 'bash build.sh'

        }

    }

}
