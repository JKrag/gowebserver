static def configureGit(def job, def repositoryUrl) {
    job.with {
        scm {
            git {
                remote {
                    name('origin')
                    url(repositoryUrl)
                }
                branch('master')
                configure {
                    it / 'extensions' << 'hudson.plugins.git.extensions.impl.PathRestriction' {
                        'includedRegions' '''\
                                            GoWebServer/.*\\.go
                                            GoWebServer/.*\\.html
                                            GoWebServer/.*\\.png
                                            version\\.txt'''.stripIndent()
                    }
                }
            }
        }
        triggers {
            scm('* * * * *')
        }
    }
}

static def configureEnv(def job, def param, def value) {
    job.with {
        properties {
            environmentVariables {
                keepSystemVariables(true)
                keepBuildVariables(true)
                env(param, value)
            }
        }
    }
}