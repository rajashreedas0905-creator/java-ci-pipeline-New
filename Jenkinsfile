pipeline {
    agent any
    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven'
        PATH = "${MAVEN_HOME}\\bin;${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rajashreedas0905-creator/java-ci-pipeline-New'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
