package edu.uwf

def createEnvironment(repo, path)
{

    dir("${path}")
    {

        def image = docker.build("integration")
        image.inside("--link database:db -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/cdep")
        {

            sh 'bash build.sh'

        }

    }

}
