package edu.uwf

def createEnvironment(repo, path)
{

    stage ("Static Analysis")
    {

        dir("${path}")
        {

            def image = docker.build("static")
            image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/medium:/maven")
            {

                sh 'bash build.sh'

            }

        }

    }

}
