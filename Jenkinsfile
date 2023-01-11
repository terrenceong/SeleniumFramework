pipeline {
    agent {
        node {
            label 'docker-agent-maven'
            }
      }
    triggers {
        pollSCM 'H/3 * * * *'
    }
    stages {
        stage('Build') {
            steps {
                echo "Check Versioning.."
                sh '''
                mvn -version
                ls -ltr
                '''
            }
        }
        stage('Test') {
            steps {
                echo "Testing.."
                sh '''
                mvn test -P Regression -D remote=true remoteURL="$REMOTE_URL"
                '''
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging....'
                sh '''
                echo "packaging stage currently  not in use"
                '''
            }
        }
    }
}