// vars/sayHello.groovy
def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."
}

def createEnvironment(repo, path, stage)
{

    dir("${path}")
    {

        def image = docker.build("${stage}")
        image.inside("-v /home/ec2-user/workspace/jenkins_pipeline/${repo}:/cdep")
        {

            sh 'bash build.sh'

        }

    }

}
