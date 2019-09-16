pipeline {
options {
        sidebarLinks {
            // use built-in image
            link('https://jira.acme.org/', 'JIRA', 'notepad.png')
            
        }
    }
	stages {
stage ('clone_git_repo')
    {
            script {
            println "Hello"
            }


}
}
}
