package edu.uwf

def createEnvironment(path, repo, url, branch)
{

    dir("${path}")
    {

        def image = docker.build("merging")
        image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/pipeline")
        {

            sh 'bash build.sh'

        }



    }

    dir("/home/ec2-user/workspace/jenkins_pipeline/${repo}")
    {

        sh 'git add -A'
        sh 'git commit -m "Packaged everything"'
        sh 'git push'
        sh 'git checkout master'
        sh 'git merge ${branch}'
        sh 'git push origin master'

    }

}
