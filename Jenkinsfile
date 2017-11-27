#!/usr/bin/env groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Builg..'
                sh 'mvn clean package'
                
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }        
        stage('Deploy') {
            steps {
                echo 'Deploying....'                                  
            }
        }
    }
}