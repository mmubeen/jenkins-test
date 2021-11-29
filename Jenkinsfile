@Library('reside-pipeline-shared@main')
import org.resideadmissions.Choices

def VersionChoices() {
   return ['1','2','3'].join('\n')
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
