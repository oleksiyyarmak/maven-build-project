#!/usr/bin/env groovy

import hudson.model.*
import hudson.EnvVars
import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
import org.apache.commons.io.FileUtils

node (label: 'master')
{
  stage ('clone_git_repo')
    {


          println 'Checkout Gitlab...'
          checkout([$class: 'GitSCM', branches: [[name: "*/master"]],
          doGenerateSubmoduleConfigurations: false,
          extensions: [[$class: 'CleanBeforeCheckout']],
          submoduleCfg: [],
          userRemoteConfigs: [[credentialsId: 'oleksiyyarmak',
          name: 'origin',
          refspec: '',
          url: 'https://github.com/oleksiyyarmak/maven-build-project.git']]
          ])

    }
  stage ('Parsing and modifying pom.xml')
    {
          script {
                    revision="2.1.${BUILD_NUMBER}-legacy-trit"
                 }
          println "Revision: ${revision}"

          def app_info = readFile(file: 'pom.xml')
          println "Test1"
          println app_info
          def xmlText = readMavenPom(file: 'pom.xml')
          println "Test2"
          println xmlText.properties['legacy.version']
          xmlText.properties['legacy.version'] = "${revision}".toString()
          println xmlText.properties['legacy.version']
          writeMavenPom(model: xmlText, file: 'pom.xml')
          println readFile(file: 'pom.xml')
          // git url: "https://github.com/oleksiyyarmak/maven-build-project.git",
          //     credentialsId: 'oleksiyyarmak',
          //     branch: master


    }
    stage ('Parsing and modifying pom.xml')
      {
        git url: "https://github.com/oleksiyyarmak/maven-build-project.git",
        credentialsId: 'oleksiyyarmak',
        branch: master
        sh "pwd"
        sh "ls -lsa"
        sh "git branch"
        sh "git add ."
        sh "git commit -m 'version revision builded'"
        sh "git push origin master --force"
      }


}
