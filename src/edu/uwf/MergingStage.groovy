package edu.uwf

class MergingStage implements Serializable
{
    def steps

    MergingStage(steps)
    {

        this.steps = steps

    }

    def createEnvironment(repo, url, branch)
    {

        steps.stage ("Merging")
        {

            steps.dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
            {

                steps.sh "git checkout master"
                steps.sh "git merge ${branch}"
                steps.sh "git push ${url} master"

            }

        }

    }

}
