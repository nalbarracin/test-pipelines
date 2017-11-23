#!/usr/bin/env groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean package'
                
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Example') {
            environment { 
                AN_ACCESS_KEY = credentials('09059df9-f6a7-4ac2-96ad-9c5aa41ec8bb')
                 
            }
            steps {
                sh 'sshpass -p $AN_ACCESS_KEY_PSW scp target/*.war nalbarracin@localhost:/opt/tomcat-latest/webapps'
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