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
                when {
                    expression {
                        currentBuild.result == null || currentBuild.result == 'SUCCESS' 
                    }
                }
                steps {
                    sh 'make publish'
                }
            }
        }
    }
}