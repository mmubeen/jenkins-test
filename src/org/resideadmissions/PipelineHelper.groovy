package org.resideadmissions

// @Library('jenkins-pipeline-aws-shared-lib') import org.resideadmissions.AwsHelper

// class Utilities implements Serializable {
//     def steps
//     Utilities(steps) {
//         this.steps = steps
//     }
//     def doArchiveToNexus(String credentials, String artifact, String artifact_registry_path){
//         try {
//             this.steps.withCredentials([steps.usernameColonPassword(credentialsId: credentials, variable: 'JENKINS_USER')]) {
//                 this.steps.sh "curl --user " + '${JENKINS_USER}' + " --upload-file ${artifact} ${artifact_registry_path}"
//             }
//         } catch (error){
//             this.steps.echo error.getMessage()
//             throw error
//         }
//     }
// }

class PipelineHelper implements Serializable{
    def steps
    def aws
    

    PipelineHelper(steps) {
        this.steps = steps
        this.aws = new AwsHelper(this.steps, )
    }

    def getImageTags(region){
        try {

                // this.steps.sh(returnStdout: false, script: "aws ecr get-login --region ${region} --no-include-email")
                // this.steps.sh(returnStdout: true, script: "aws ecr list-images --repository-name reside-integrations --region ${region} 2>&1 | tee result.json")
                // def tagsList = readFile('result.json').trim()
                // echo "tagsList: ${tagsList}"
                // def parsedJSON = new groovy.json.JsonSlurperClassic().parseText(tagsList) //imageTag
                // def dropdownlist = []
                // parsedJSON.imageIds.each { dropdownlist.push('"' + it.imageTag + '"') }
                // return dropdownlist
                def docker_password = this.aws.ecrGetLogin(region)
                def aws_erc_account = this.getAwsAccount('dev') 
                this.steps.sh(script: "echo ${docker_password} | docker login --username AWS --password-stdin ${aws_erc_account}", returnStdout:true)
                def images = this.aws.ecr_list_images("reside-integrations",region)
                return images

        } catch (error){
            this.steps.echo error.getMessage()
            throw error
        }
    }

    // def loginToAWSEcrService(region) {
    //     awsloginstring = `aws ecr get-login --region ${region} --no-include-email`
    //     this.steps.sh '''
    //         eval `aws ecr get-login --region ${region} --no-include-email`
    //     '''
    // }

    // def getCredentialsId(env) {
    //     switch(env) {
    //         case 'dev':
    //         return "aws-reside-dev-credentials"

    //         case 'test':
    //         return "aws-reside-test-credentials"

    //         case 'stg':
    //         return "aws-reside-stg-credentials"

    //         default:
    //         return "aws-reside-credentials"
    //     }
    // }

    def getAwsAccount(env) {
        switch(env) {
            case 'dev':
            return "380505780076.dkr.ecr.us-east-2.amazonaws.com"

            case 'test':
            return "462285824561.dkr.ecr.us-east-2.amazonaws.com"

            case 'stg':
            return "578511621719.dkr.ecr.us-east-2.amazonaws.com"

            default:
            return "402516319689.dkr.ecr.us-east-2.amazonaws.com"
        }
    }
}