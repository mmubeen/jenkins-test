package org.resideadmissions

class Choices implements Serializable{
    def steps 

    Choices(steps){
        this.steps = steps
    }

    def getVersions(){
        this.steps.withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
            def pipeine_helper = new PipelineHelper(this.steps)
            return pipeine_helper.getImageTags("us-east-2")
        }
        // return ['1','2','3'].join('\n')
    }

    def renderVersionChoices(){
        return this.steps.properties([
                parameters([
                    [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT',
                        description: 'Choose environment category.',
                        name: 'VERSION',
                        script: [
                            $class: 'GroovyScript',
                            script: [sandbox: false, script: '''
                                return this.getVersions()
                            '''
                            ]
                        ]
                    ]
                ])
            ])
    }
}