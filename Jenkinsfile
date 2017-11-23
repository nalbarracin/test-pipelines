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
        	withCredentials([usernameColonPassword(credentialsId: 'nalbarracin', variable: 'USERPASS')]) {
    		sh '''
      		set +x
      		sshpass -p $USERPASS scp target/*.war nalbarracin@localhost:/opt/tomcat-latest/webapps
    		'''
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