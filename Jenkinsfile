@Library('reside-pipeline-shared@main') _
import('org.resideadmissions.AWShelper')


pipeline {
    agent  any
    stages {
        stage("Hello World") {
            steps {
                def aws_helper = new AWShelper(this)
                echo aws_helper.getImageTags
            }
        }
    }
}
