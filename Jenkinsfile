@Library('reside-pipeline-shared@main')
import org.resideadmissions.PipelineHelper


node {    
    
    docker.image("aws/aws-cli:latest").inside('-u 0:0 -v /var/run/docker.sock:/var/run/docker.sock') {
        withAWS(credentials:"aws-master-credential",region:"us-east-2"){
            pipeine_helper = new PipelineHelper(this)
            echo pipeine_helper.getImageTags("us-east-2")
        }
    }        
}
