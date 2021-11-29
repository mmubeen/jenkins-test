@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper


node {    
    withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
        pipeine_helper = new PipelineHelper(this)
        echo pipeine_helper.getImageTags("aws-master-credential","us-east-2")
    }        
}
