pipeline {
	stages {
stage ('clone_git_repo')
    {
	steps {
            script {
            println "Hello"
            }
			sidebarLinks {
            // use built-in image
            link('https://jira.acme.org/', 'JIRA', 'notepad.png')
            
        }
	}

}
}
}
