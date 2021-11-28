@Library('reside-pipeline-shared@main')
import org.resideadmissions.AWShelper


node {    
     
    aws_helper = new AWShelper(this)
    echo aws_helper.getImageTags("aws-reside-dev-credentials","us-east-2")
               
}
