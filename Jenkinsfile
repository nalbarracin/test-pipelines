#!/usr/bin/env groovy
pipeline {
    agent 

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mv clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue nonetheless
                */
                sh 'make check || true' 
                junit '**/target/*.xml'
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