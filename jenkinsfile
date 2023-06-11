pipeline {
    agent any
    stages {
        stage("verify tooling") {
            steps {
                sh '''
                  docker version
                  docker info
                  docker compose version
                  curl --version
                '''
            }
        }
        
        stage('Start container') {
            steps {
                sh 'docker compose down'
                sh 'docker rm -f $(docker ps -a -q) || true'
                sh 'docker volume rm $(docker volume ls -q) || true'
                sh 'docker compose ps'
                sh 'docker compose up -d --no-color --wait'
            }
        }
    }
    post {
        always {
            sh 'docker compose ps'
        }
    }
}
