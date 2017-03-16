package edu.uwf

class MergingStage implements Serializable
{
    def steps

    MergingStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(path, repo, url, branch)
    {

        steps.stage ("Merging")
        {

            dir("${path}")
            {

                def image = docker.build("merging")
                image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline")
                {

                    sh 'bash build.sh ${branch} ${url}'

                }

            }

        }

    }

}
