package edu.uwf

class StaticStage implements Serializable
{

    def steps

    StaticStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo, path)
    {

        try
        {

            def static_image
            dir("${path}")
            {

                static_image = docker.build(${repo})
                //sh "docker build -t ${repo} ${path}"
                sh "docker run -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --rm ${repo}"

            }

            dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
            {
                static_image.run('-v ./:/pipeline --rm ${repo}')
            }

        }
        catch (e)
        {
            currentBuild.result = "UNSTABLE"
        }

    }

    def thing(args)
    {
        steps.echo "${args}"
    }

}
