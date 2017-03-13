package edu.uwf

def createEnvironment(repo, path)
{

    stage ("Unit Testing")
    {

        dir("${path}")
        {

            def image = docker.build("unit")
            image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/maven")
            {

                sh 'bash build.sh'

            }

        }

    }

}
