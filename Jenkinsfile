@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper


node {    
    withAWS(credentials:"aws-master-credential",region:"us-east-2"){
        pipeine_helper = new PipelineHelper(this)
        echo pipeine_helper.getImageTags("us-east-2")
    }        
}
