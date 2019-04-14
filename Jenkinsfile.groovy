pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean pipelineAsCodeExample'
                post {
                    success {
                        echo 'Now archiving...'
                        archiveArtifacts artifacts: '**/target/*.war'
                    }
                }
            }
        }

    }
}