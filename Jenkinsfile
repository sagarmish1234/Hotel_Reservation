pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sagarmish1234/Hotel_Reservation.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Build Images') {
            steps {
                bat 'docker-compose up -d'
            }
        }


    }
}