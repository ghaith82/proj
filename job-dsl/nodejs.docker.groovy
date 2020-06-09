job('NodeJS Docker example') {
    scm {
        git('git://github.com/ghaith82/proj.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('ghaith82')
            node / gitConfigEmail('ghaith.alkayyem@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ghaith82/nodejs-proj')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
