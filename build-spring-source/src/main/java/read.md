

## 使用idea 构建 导入spring 源码

    准备：
    
    idea 2019.1
    gradle 5.6 (大于5.6 小于6)
    spring5.2.5
    
    
## 准备

    下载好spring source    
    
    配置gradle
    
    国内源：
    
    在.gradle 下面创建 init.gradle 文件
    
allprojects{
    repositories {
        def ALIYUN_REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/groups/public'
        def ALIYUN_JCENTER_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
        all { ArtifactRepository repo ->
            if(repo instanceof MavenArtifactRepository){
                def url = repo.url.toString()
                if (url.startsWith('https://repo1.maven.org/maven2') || url.startsWith('http://repo1.maven.org/maven2')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                    remove repo
                }
                if (url.startsWith('https://jcenter.bintray.com/') || url.startsWith('http://jcenter.bintray.com/')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                    remove repo
                }
            }
        }
        maven {
            url ALIYUN_REPOSITORY_URL
            url ALIYUN_JCENTER_URL
        }
    }
buildscript{
        repositories {
            def ALIYUN_REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/groups/public'
            def ALIYUN_JCENTER_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
            all { ArtifactRepository repo ->
                if(repo instanceof MavenArtifactRepository){
                    def url = repo.url.toString()
                    if (url.startsWith('https://repo1.maven.org/maven2') || url.startsWith('http://repo1.maven.org/maven2')) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                        remove repo
                    }
                    if (url.startsWith('https://jcenter.bintray.com/') || url.startsWith('http://jcenter.bintray.com/')) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                        remove repo
                    }
                }
            }
            maven {
                url ALIYUN_REPOSITORY_URL
                url ALIYUN_JCENTER_URL
            }
        }
    }
}   

### 在spring-framework目录下的build.gradle中，第一行加入
buildscript {
   repositories {
      maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
      maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter' }
      maven { url 'http://maven.aliyun.com/nexus/content/repositories/google' }
      maven { url 'http://maven.aliyun.com/nexus/content/repositories/gradle-plugin' }
   }
   dependencies {
      classpath 'org.springframework.boot:spring-boot-gradle-plugin:1.5.21.RELEASE'
   }
}

### 在plugins后面加入

allprojects {
   repositories {
      maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
      maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter' }
      maven { url 'http://maven.aliyun.com/nexus/content/repositories/google' }
      maven { url 'http://maven.aliyun.com/nexus/content/repositories/gradle-plugin' }
   }
}   

    注释掉
    
    
id 'io.spring.gradle-enterprise-conventions' version '0.0.2'


     修改spring-framework文件夹下的settings.gradle文件，将第4行改为
     
maven{ url 'http://maven.aliyun.com/nexus/content/groups/public/'}


    在spring-oxm目录下执行
    spring-code
    可以执行build
gradle :spring-oxm:compileTestJava


    导入后记得排除aspectJ
    右键项目 选择UnLoader 卸载
    
    
## 项目结构

    spring-context
    下面 build.gradle
    optional 改为 compile
    
    重新bulid task
    
    
    // 
    打开项目结构
    点击File -> Project Structure -> Libraries -> + -> Java，
    然后选择spring-framework/spring-core/kotlin-coroutines/build/libs/kotlin-coroutines-5.2.4.BUILD-SNAPSHOT.jar，
    在弹出的对话框中选择spring-core.main，在重新build项目即可。
    
    
## 测试

    新建 gradle项目 spring test
    
    测试吧
        
    

