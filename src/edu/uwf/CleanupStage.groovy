package edu.uwf

class CleanupStage implements Serializable
{
    def steps

    CleanupStage(steps)
    {

        this.steps = steps

    }

    def cleanup(repo)
    {

        steps.sh "sudo rm -rf /home/ec2-user/workspace/jenkins_pipeline/${repo}"
        steps.sh "sudo rm -rf /home/ec2-user/workspace/jenkins_pipeline/${repo}\\@tmp"

    }

}
