#!/usr/bin/env groovy
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..' 
                sh 'mvn test'
            }
        }
        stage('Deploy Local') {
            environment { 
                USERPASS = credentials('tomcat_deployer') 
                SERVER = 'localhost'
            }
        	steps{
        	    echo 'Deployando localmente....'
        	    script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        sh 'sshpass -p $USERPASS_PSW scp -o StrictHostKeyChecking=no target/*.war $USERPASS_USR@$SERVER:/opt/tomcat-latest/webapps'
                    }else {
                        currentBuild.result = "FAILURE" 
                    }
        	    }
        	}
        }
        stage('Deploy Apolo') {
            environment { 
                USERPASS = credentials('deploy-apolo') 
                SERVER = '192.168.8.5'
            }
        	steps{
        	    echo 'Deployando en Apolo...'
        	    script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        sh 'sshpass -p $USERPASS_PSW scp -o StrictHostKeyChecking=no target/*.war $USERPASS_USR@$SERVER:/opt/tomcat-latest/webapps'
                    }else {
                        currentBuild.result = "FAILURE"
                    }
        	    }
        	}
        }
    }
}