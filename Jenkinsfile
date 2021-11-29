@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper

deriveJobParams()

node {    
    withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
        pipeine_helper = new PipelineHelper(this)
        echo pipeine_helper.getImageTags("us-east-2")
    }
       
}
