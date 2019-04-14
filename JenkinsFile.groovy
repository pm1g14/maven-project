pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean pipelineAsCodeExample'
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