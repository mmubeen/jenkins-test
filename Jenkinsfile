@Library('reside-pipeline-shared@main')
import org.resideadmissions.Choices

pipeline {
    agent any
    parameters {
       choice(choices: {return new Choices(this).getVersions()}, description:  'Pick a version', name: 'VERSION')
    }
    stages {
        stage('test') {
            steps {
                script {
                    sh '''
                        echo 'test'
                    '''
                }
            }
        }
    }
}
