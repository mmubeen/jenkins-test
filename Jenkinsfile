@Library('reside-pipeline-shared')

pipeline {
    agent  any
    stages {
        stage("Hello World") {
            steps {
                shared() // example2() uses the default parameter. you can also try: ``example2 "hi"`` or ``example2 "hello"`` 
            }
        }
    }
}
