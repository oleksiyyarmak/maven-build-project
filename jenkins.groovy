#!/usr/bin/env groovy

import hudson.model.*
import hudson.EnvVars
import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
import org.apache.commons.io.FileUtils

node (label: 'master')
{
          stage ('clone_git_repo') {

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
          stage ('parse') {

            def app_info = readFile(file: 'pom.xml')
            println "Trftff"
            // println app_info
            def xmlText = readMavenPom(file: 'app_info')
            println "Bla"
            // println xmlText.@'legacy.version'


		}

}
