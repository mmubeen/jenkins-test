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
}