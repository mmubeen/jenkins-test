import org.resideadmissions.Choices

def call() {
    properties([
        parameters([
            [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT',
                description: 'Choose environment category.',
                name: 'VERSION',
                script: [
                    $class: 'GroovyScript',
                    script: [sandbox: true, script: '''
                        def choices = new Choices(this)
                        return choices.getVersions()
                    '''
                    ]
                ]
            ]
        ])
    ])
}