@Library('reside-pipeline-shared')

def testChoices(){
 return[1,2,3]    
}

def loginToAWSEcrService(region) {
    withEnv(["REGION=$region"]) {
        stage("Login To AWS Ecr Service") {
            sh '''
                eval `aws ecr get-login --region $REGION --no-include-email`
            '''
        }
    }
}

def getCredentialsId(env) {
   switch(env) {
     case 'dev':
      return "aws-reside-dev-credentials"

     case 'test':
      return "aws-reside-test-credentials"

     case 'stg':
      return "aws-reside-stg-credentials"

     default:
      return "aws-reside-credentials"
   }
}

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

def pullDockerImage(region, sourceEnv, image, imageTag) {
    withEnv(["SOURCE_ENV=$sourceEnv", "REGION=$region", "IMAGE=$image", "IMAGE_TAG=$imageTag"]) {
        docker.image("resideadmissions/aws-cli:latest").inside('-u 0:0 -v /var/run/docker.sock:/var/run/docker.sock') {
            loginToAWSEcrService(env.REGION)
            sourceImage = "$env.SOURCE_ENV/$env.IMAGE:$env.IMAGE_TAG"
            stage("Pull Docker Image") {
                echo "Docker Image: ${sourceImage}"
                sh '''
                    docker pull ${sourceImage} 
                ''' 
            }
        }
    }
}

def pushDockerImage(region, sourceEnv, targetEnv, image, imageTag) {
    withEnv(["SOURCE_ENV=$sourceEnv", "TARGET_ENV=$targetEnv", "REGION=$region", "IMAGE=$image", "IMAGE_TAG=$imageTag"]) {
        docker.image("resideadmissions/aws-cli:latest").inside('-u 0:0 -v /var/run/docker.sock:/var/run/docker.sock') {
            loginToAWSEcrService(env.REGION)
            sourceImage = "$env.SOURCE_ENV/$env.IMAGE:$env.IMAGE_TAG"
            targetImage = "$env.TARGET_ENV/$env.IMAGE:$env.IMAGE_TAG"
            stage("Pull Docker Image") {
                echo "Docker Image: ${targetImage}"
                sh '''
                    docker tag ${sourceImage} ${targetImage}
                    docker push ${targetImage}
                ''' 
            }
        }
    }
}

def getTagFromDevEcr(region, credentialsId, awsDevAccountId){
    withEnv(["AWS_CREDENTIALS_ID=${credentialsId}", "REGION=${region}"]) {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: env.AWS_CREDENTIALS_ID, secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
            docker.image("resideadmissions/aws-cli:latest").inside('-u 0:0 -v /var/run/docker.sock:/var/run/docker.sock') {
                loginToAWSEcrService(env.REGION)
                stage("Get ecr image list") {
                    sh(returnStdout: true, script: "aws ecr list-images --repository-name reside-integrations --region $REGION 2>&1 | tee result.json")
                    def tagsList = readFile('result.json').trim()
                    echo "tagsList: ${tagsList}"
                    def parsedJSON = new groovy.json.JsonSlurperClassic().parseText(tagsList) //imageTag
                    def dropdownlist = []
                    parsedJSON.imageIds.each { dropdownlist.push('"' + it.imageTag + '"') }
                    return dropdownlist
                }
            }
        }
    }
}

def parseJSON(json) {
    return new groovy.json.JsonSlurperClassic().parseText(json)
}
                    
def get_image_tag_list(){
    def awsDevAccountId = "380505780076"
    def credentialsId = "aws-reside-dev-credentials"
    def region = "us-east-2"
    return getTagFromDevEcr(region, credentialsId, awsDevAccountId)
}

def get_image_types_list(){
    return ["reside-app","reside-fe","reside-integrations","reside-content"]
}

String buildScript(List values){
    echo "values = $values"
    return "return $values"
}

String imageTagListScript = buildScript(get_image_tag_list())
echo "values_out = $imageTagListScript"

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
                                script:  imageTagListScript
                            ]
                        ]
                    ]
                ]),
            ]
        )

pipeline{
    agent any
    stages {
        stage('Test') {
            steps {
                script {
                    def region = "us-east-2"
                    def credentialsId = getCredentialsId('dev')
                    def sourceEnv = getAwsAccount('dev')
                    def targetEnv = getAwsAccount('test')
                    def image = "reside-web"
                    def imageTag = "${params.ImageTag}"
                    if ("${imageTag}"!= "ERROR") {
                        withEnv(["AWS_CREDENTIALS_ID=${credentialsId}"]) {
                            withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: env.AWS_CREDENTIALS_ID, secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                                pullDockerImage(region, sourceEnv, image, imageTag)
                            }
                        }

                        def targetEnvCredId = getCredentialsId('test')
                        withEnv(["AWS_CREDENTIALS_ID=${targetEnvCredId}"]) {
                            withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: env.AWS_CREDENTIALS_ID, secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                                pushDockerImage(region, sourceEnv, targetEnv, image, imageTag)
                            }
                        }
                    }
                   
                }
            }
        }
    }
}

