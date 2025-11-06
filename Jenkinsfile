pipeline {
  agent any

  environment {
    // Optional: set MAVEN_HOME or Java home if needed, or use tool() to select installed tools
    // MAVEN_CMD = "mvn"
  }

  stages {
    stage('Checkout') {
      steps {
        // checkout from the SCM configured in the Jenkins job (for pipeline from SCM this will work)
        checkout scm
      }
    }

    stage('Build') {
      steps {
        // Use Maven to compile & package. -B = batch mode for non-interactive logs
        sh 'mvn -B -DskipTests=false clean package'
      }
    }

    stage('Run Unit Tests') {
      steps {
        // Run tests explicitly (if not run during package). Adjust command if your project uses different lifecycle
        sh 'mvn -B test'
      }
      post {
        success {
          echo "Unit tests passed"
        }
        failure {
          echo "One or more tests failed"
        }
      }
    }

    stage('Publish Results & Artifacts') {
      steps {
        // Archive jar/war or other build outputs
        archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        // Capture junit XML test reports so Jenkins shows test results in UI
        // surefire and failsafe default output path: target/surefire-reports/*.xml
        junit 'target/surefire-reports/*.xml'
      }
    }
  }

  post {
    always {
      // Always collect test report (even if earlier junit step fails), and print console link
      echo "Build finished. Check the Jenkins job page for details."
    }
    success {
      echo 'Build succeeded ✅'
    }
    failure {
      echo 'Build failed ❌'
    }
  }
}
