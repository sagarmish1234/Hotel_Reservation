pipeline {
    agent any

    options{
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    triggers {
        pollSCM('5 * * * *')
    }



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

        stage('Run Containers') {
                    steps {
                        bat 'start.bat'
                    }
                }

    }
}