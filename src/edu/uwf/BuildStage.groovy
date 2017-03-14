package edu.uwf

class BuildStage implements Serializable
{
    def steps

    BuildStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(script, repo, path)
    {

        steps.stage ("Build")
        {

            // Building the docker image from the Dockerfile
            steps.sh "docker build -t jpipeline ${path}"
            steps.sh "docker run -t -d -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --name \"jpipeline\" jpipeline"
            steps.sh "docker exec jpipeline bash -c 'cd /maven/${repo}/MediumFX/;mvn compile'"

            steps.sh "docker stop jpipeline"
            steps.sh "docker rm jpipeline"

        }

    }

}
