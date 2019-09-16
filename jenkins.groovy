pipeline {
    agent {
    node {
      label 'master'
       }
         }
	stages {
stage ('clone_git_repo')
    {
	steps {
            script {
            println "Hello"
            }
			sidebarLinks {
            // use built-in image
            links('https://jira.acme.org/', 'JIRA', 'notepad.png')
            
        }
	}

}
}
}
