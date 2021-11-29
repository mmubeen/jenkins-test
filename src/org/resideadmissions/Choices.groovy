package org.resideadmissions

class Choices implements Serializable {

    Choices(steps){
        this.steps = steps
    }

    def getVersions(){
        this.steps.withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
            pipeine_helper = new PipelineHelper(this)
            return pipeine_helper.getImageTags("us-east-2")
        }
    }

    def renderVersionChoices(){
        properties([
            parameters([
                [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT',
                    description: 'Choose environment category.',
                    name: 'VERSION',
                    script: [
                        $class: 'GroovyScript',
                        script: [sandbox: true, script: '''
                            return this.getVersions()
                        '''
                        ]
                    ]
                ]
            ])
        ])
    }
}