@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper

properties(
            [
                [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
                // parameters(
                //     [
                //         choice(choices: script{get_image_tag_list()}, description: 'select an image type', name: 'image_type'),
                //         choice(choices: script{get_image_types_list()}, description: 'select a image version', name: 'image_version'),
                //         activeChoiceParam('CHOICE-1') {
                //             description('Allows user choose from multiple choices')
                //             filterable()
                //             choiceType('SINGLE_SELECT')
                //             groovyScript {
                //                 script('["choice1", "choice2"]')
                //                 fallbackScript('"fallback choice"')
                //             }
                //         }
                //     ]
                // )
                parameters([
                        [
                            $class: 'ChoiceParameter', 
                            choiceType: 'PT_SINGLE_SELECT',   
                            name: 'Version', 
                            script: [
                                $class: 'GroovyScript', 
                                fallbackScript: [
                                    classpath: [], 
                                    sandbox: false, 
                                    script: 'return [\'Could not get Env\']'
                                ], 
                            script: [
                                classpath: [], 
                                sandbox: false,
                                script:  '''
                                    withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
                                        pipeine_helper = new PipelineHelper(this)
                                        return pipeine_helper.getImageTags("us-east-2")
                                    }
                                '''
                            ]
                        ]
                    ]
                ]),
            ]
        )

node {    
    withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
        pipeine_helper = new PipelineHelper(this)
        echo pipeine_helper.getImageTags("us-east-2")
    }
       
}
