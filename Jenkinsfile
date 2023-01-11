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
                mvn test -P Purchase -D remote=true -D remoteURL="$remoteURL"
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