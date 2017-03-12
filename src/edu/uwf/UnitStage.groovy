package edu.uwf

def createEnvironment(repo, path)
{

    stage ("Unit Testing")
    {

        dir("${path}")
        {

            def image = docker.build("junit")
            image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/medium:/maven")
            {

                sh 'bash unit-test.sh'

            }

        }

    }

}
