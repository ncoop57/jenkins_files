package edu.uwf

def createEnvironment(repo, path)
{

    stage ("Build")
    {

        dir("${path}")
        {

        // Building the docker image from the Dockerfile
        def image = docker.build("build")

        image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/cdep")
        {

            sh 'bash build.sh'

        }

    }

}
