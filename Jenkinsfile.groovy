pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean maven-project'
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