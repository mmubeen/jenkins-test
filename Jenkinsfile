@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper


node {    
    // sh '''
    //     cat ~/my_password.txt | docker login --username foo --password-stdin
    // '''
    
    withAWS(credentials:"aws-reside-dev-credentials",region:"us-east-2"){
        pipeine_helper = new PipelineHelper(this)
        echo pipeine_helper.getImageTags("us-east-2")
    }
       
}
