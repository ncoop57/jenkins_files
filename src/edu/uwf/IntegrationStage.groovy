package edu.uwf

def createEnvironment(repo, path)
{

    stage ("Integration Testing")
    {

        dir("${path}")
        {

            def image = docker.build("integration")
            image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/maven")
            {

                sh 'ls /maven/'

            }

        }

    }

}
