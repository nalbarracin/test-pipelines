#!/usr/bin/env groovy
try{
    stage('Clone'){
        node{
            cleanWs()
            sh 'git clone https://github.com/nalbarracin/test-pipelines/ .'
        }
    }

    stage('Clean') {
        node {
            echo 'Building..'
            sh 'mvn clean package'
        }
    }

    stage('Test') {
        node {
            echo 'Testing..'
            sh 'mvn test'
        }
    }
}
catch(error){
    echo 'Error..'
}
finally{
	echo "Workspace Clean"
}