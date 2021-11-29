def call() {
    properties([
        parameters([
            [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT',
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