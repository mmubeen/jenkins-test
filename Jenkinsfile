@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper


node {    
    withAWS("aws-reside-dev-credentials","us-east-2"){
        aws_helper = new PipelineHelper(this)
        echo aws_helper.getImageTags("aws-reside-dev-credentials","us-east-2")
    }        
}
