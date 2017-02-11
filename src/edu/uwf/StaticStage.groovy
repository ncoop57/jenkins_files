package edu.uwf

class StaticStage implements Serializable
{
    def steps

    StaticStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo)
    {

        try
        {

            def staticImage
            steps.dir("${path}")
            {

                staticImage = steps.docker.build(${repo})
                //sh "docker build -t ${repo} ${path}"
                //sh "docker run -v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline --rm ${repo}"

            }

            steps.dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
            {
                staticImage.run("-v ./:/pipeline --rm ${repo}"")
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
