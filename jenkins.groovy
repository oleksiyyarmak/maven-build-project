pipeline {
    agent {
    node {
      label 'master'
      customWorkspace "${JENKINS_HOME}/workspace/${JOB_NAME}/${BUILD_NUMBER}"
    }
         }
    tools {
        maven 'maven'
        jdk 'JDK'
    }
       stages {
        stage ('clone_git_repo') {
            steps {
                git credentialsId: 'oleksiyyarmak', url: 'https://github.com/oleksiyyarmak/maven-build-project.git'
                sh "pwd"
                sh "ls -la"
            }
        }
       stage ('build_maven') {
            steps {
              dir("${JENKINS_HOME}/workspace/${JOB_NAME}/${BUILD_NUMBER}/gs-maven-master/initial") {
                    sh "ls -la"
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
            		}
					}
		}	
		stage ('tests') {
			steps {
                   dir("${JENKINS_HOME}/workspace/${JOB_NAME}/${BUILD_NUMBER}/gs-maven-master/test") {
                    sh "pwd"
                    sh "ls -la"
                    sh 'sh run.sh' 
                }
            }
                 
        }  
		}
 }

