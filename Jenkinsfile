#!/usr/bin/env groovy
pipeline {
    agent any
    
    stages {
        stage('Clean and Clone'){
            steps 
            {
                script{
                    cleanWs()
                    sh 'git clone https://github.com/nalbarracin/test-pipelines/ .'
                }
        	}
        }
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
        stage('Deploy Local') {
            environment { 
                USERPASS = credentials('tomcat_deployer') 
                SERVER = 'localhostsdrtsdrtssdr'
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
    }
    post{
        always{
            echo "Workspace Clean"
            cleanWs()
        }
    }
}