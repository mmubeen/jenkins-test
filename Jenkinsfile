@Library('reside-pipeline-shared@main')
import org.resideadmissions.Choices

def choices = new Choices(this)
choices.renderVersionChoices()

pipeline {
    agent any
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
