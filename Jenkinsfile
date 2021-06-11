pipeline {
    agent { label 'dev' }

 stages {
        stage('Build') {
            steps {
                sh 'pwd'
		sh './mvnw install -Dmaven.test.skip=true '
                sh './mvnw package -Dmaven.test.skip=true'
            }
        }
        stage('Deploy') {
            steps {
                 script {
                        sh 'docker build -t api-ebanking .'

                    try {
			sh 'docker rm api-ebanking -f'
                        sh 'docker run --name api-ebanking --net mysql_default --restart always -d -p 3001:8888 api-ebanking'

                    }
                    catch (Exception e) {
                        sh 'docker run --name api-ebanking --restart always --net mysql_default -d -p 3001:8888 api-ebanking'
                    }
                }
            }
        }
    }
}
