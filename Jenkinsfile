@Library('shared_libraries') import edu.uwf.*

node('docker_box')
{

    def text

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

    echo text

}
