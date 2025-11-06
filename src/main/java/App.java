pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Use bat instead of sh for Windows
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Use bat for running tests
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                // Use bat for packaging
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo '✅ Build and tests completed successfully!'
        }
        failure {
            echo '❌ Build failed. Check logs for details.'
        }
    }
}