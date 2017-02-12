@Library('shared_libraries') import edu.uwf.*

node('docker_box')
{

    def text
    def url = "git@github.com:UWF-HMCSE-CS/SEMDEVOPS011.git"
    def repo = "semdevops011"
    def branch = "feature"

    dir("/home/ec2-user/workspace")
    {

        text = sh (script: 'cat test.json', returnStdout: true).trim()

    }

    stage ("Checkout")
    {

        dir('/home/ec2-user/workspace/DevOps')
        {

            def checkout = new CheckoutStage(steps)

            checkout.updateTesterRepo()
            checkout.checkoutRepo(url, repo, branch)
            echo 'Updated the tester repo'

        }

    }

    stage ("Static Analysis")
    {

        dir('/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget')
        {

            def staticAnalysis = new StaticStage(steps)

            staticAnalysis.createEnvironment(repo, "/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget")

        }

    }

    echo text

}
