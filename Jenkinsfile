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
                SAUCE_ACCESS = credentials('09059df9-f6a7-4ac2-96ad-9c5aa41ec8bb')
                 
            }
            steps {
            	sh 'echo $SAUCE_ACCESS_PSW'
            	sh 'echo SAUCE_ACCESS_PSW'
                sh 'sshpass -p SAUCE_ACCESS_PSW scp target/*.war SAUCE_ACCESS_USR@localhost:/opt/tomcat-latest/webapps'
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