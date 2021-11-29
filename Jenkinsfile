@Library('reside-pipeline-shared@main')
import org.resideadmissions.Choices

def VersionChoices() {
   def choices = new Choices()
   return choices.getVersions()
}


properties([

    parameters([
        choice(name: 'PARAM', choices: VersionChoices(), description: 'Choice'),
    ])
])

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
