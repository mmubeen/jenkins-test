@Library('reside-pipeline-shared@main') _


pipeline {
    agent  any
    stages {
        stage("Hello World") {
            steps {
                def aws_helper = new org.resideadmissions.AWShelper(this)
                echo aws_helper.getImageTags
            }
        }
    }
}
