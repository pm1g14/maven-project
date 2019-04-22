pipeline {
    agent any

    parameters {
        string(name: 'tomcat-staging', defaultValue: 'localhost', description: 'some desc')
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
                bat 'cp **/target/*.war ${params.tomcat-staging}:C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps'
            }
        }

    }
}