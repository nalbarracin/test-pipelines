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
        	steps {
	        	withCredentials([usernameColonPassword(credentialsId: '09059df9-f6a7-4ac2-96ad-9c5aa41ec8bb', variable: 'USERPASS')]) {
	    		sh '''
	      		set +x
	      		sshpass -p $USERPASS scp -o StrictHostKeyChecking=no target/*.war nalbarracin@localhost:/opt/tomcat-latest/webapps
	    		'''
	    		}
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