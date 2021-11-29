def call() {
    properties([
        parameters([
            [$class: 'ChoiceParameter', choiceType: 'PT_SINGLESELECT',
                description: 'Choose environment category.',
                name: 'ENVIRONMENT',
                script: [
                    $class: 'GroovyScript',
                    script: [sandbox: true, script: 'return ["QA", "DEV", "PROD"]']
                ]
            ]
        ])
    ])
}