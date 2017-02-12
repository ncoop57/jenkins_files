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

                sh "git checkout master"
                sh "git merge ${branch}"
                sh "git push ${url} master"

            }

        }

    }

}
