@Library('shared_libraries') import edu.uwf.*

node('docker_box')
{

    def text
    def repo = 'https://github.com/UWF-HMCSE-CS/SEMDEVOPS001'

    dir('/home/ec2-user/workspace')
    {

        text = sh (script: 'cat test.json', returnStdout: true).trim()

    }

    stage 'Checkout'

    dir('/home/ec2-user/workspace/DevOps')
    {

        def checkout = new CheckoutStage(steps)

        checkout.updateTesterRepo()
        echo 'Updated the tester repo'

    }

    Stage 'Staging'
    dir('/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget')
    {

        def static_analysis = new StaticStage(step)

        static_analysis.createEnvironment(repo, '/home/ec2-user/workspace/DevOps/tests/phpcs/Gadget')

    }

    echo text

}
