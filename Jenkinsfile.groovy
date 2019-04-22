pipeline {
    agent any

    parameters {
        string(name: 'tomcat_staging', defaultValue: 'localhost', description: 'some desc')
    }

    triggers {
        pollSCM('* * * * *')
    }

    tools {
        maven 'maven'
    }
    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            
            }
             post {
                    success {
                        echo 'Now archiving...'
                        archiveArtifacts artifacts: '**/target/*.war'
                    }
                }
        }
        stage('Deploy to staging') {
            steps {
            //    build job: 'deploy-to-staging'
                bat "winscp **/target/*.war ${params.tomcat_staging}:C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps"
            }
        }

    }
}