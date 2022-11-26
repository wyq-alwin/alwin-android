//package com.alwin.buildsrc
//
//import org.gradle.api.DefaultTask
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.api.file.FileCollection
//import org.gradle.api.tasks.TaskAction
//import org.gradle.internal.impldep.org.apache.ivy.plugins.namespace.Namespace
//
////Extension类
//class PermissionExtension {
//    ArrayList<String> checkPermissions
//}
//
//class MiniGamePermissionPlugin implements Plugin<Project> {
//
//    static def variantNames = new ArrayList()
//
//    @Override
//    void apply(Project project) {
//        def permissionExtension = project.extensions.create("CheckPermissionExtension", PermissionExtension)
//        def appExtension = project.extensions.findByType(AppExtension.class)
//
//        project.afterEvaluate {
//            appExtension.getApplicationVariants().findAll {
//                if (!variantNames.contains(it.name)) {
//                    variantNames.add(it.name)
//                }
//            }
//
//            variantNames.each {
//                //创建Task,并设置分组
//                CheckPermissionTask checkPermissionTask = project.getTasks().create(String.format("MiniGameCheck%sPermissionTask", it.capitalize()), CheckPermissionTask)
//                checkPermissionTask.group = "MiniGameCheckPermission"
//                setCheckPermissionTaskData(project, checkPermissionTask, it.capitalize(), permissionExtension.checkPermissions)
//            }
//        }
//    }
//
//    private static void setCheckPermissionTaskData(Project project, CheckPermissionTask checkPermissionTask, String name, ArrayList<String> checkPermissions) {
//        try {
//            //找到处理Manifest的Task
//            ProcessApplicationManifest processManifestTask = project.getTasks().getByName(String.format("process%sMainManifest", name))
//            //设置Manifest文件和需要检查的权限集合
//            checkPermissionTask.setData(processManifestTask.getManifests(), checkPermissions)
//        } catch (Exception e) {
//            e.printStackTrace()
//        }
//    }
//}
//
//class CheckPermissionEntity {
//
//    def permissionName = ""
//
//    def hasPermission = false
//}
//
//class CheckPermissionTask extends DefaultTask {
//
//    private static def manifestCollection
//    private static def checkPermissions = new ArrayList<CheckPermissionEntity>()
//    private static def android = new Namespace("http://schemas.android.com/apk/res/android", "android")
//
//    @TaskAction
//    void doTaskAction() {
//        manifestCollection.each {
//            handlerVariantManifestFile(it)
//        }
//    }
//
//    static void setData(FileCollection collection, ArrayList<String> checkPermissions) {
//        manifestCollection = collection
//        this.checkPermissions.clear()
//        checkPermissions.each {
//            def entity = new CheckPermissionEntity()
//            entity.permissionName = it
//            entity.hasPermission = false
//            this.checkPermissions.add(entity)
//        }
//    }
//
//    static void handlerVariantManifestFile(File manifestFile) {
//        if (!manifestFile.exists()) {
//            println("manifestFile do not exists")
//            return
//        }
//
//        //每个Manifest读取前先把是否有权限置为false
//        checkPermissions.each {
//            it.hasPermission = false
//        }
//
//        try {
//            XmlParser xmlParser = new XmlParser()
//            def node = xmlParser.parse(manifestFile)
//            //获取user-permission标签
//            NodeList nodeList = node.get("uses-permission")
//            if (!nodeList.isEmpty()) {
//                checkPermissions.each {
//                    nodeList.find { permissionNode ->
//                        def permissionName = permissionNode.attributes()[android.get("name")]
//                        if (permissionName == it.permissionName) {
//                            it.hasPermission = true
//                        }
//                        return it.hasPermission
//                    }
//                }
//            }
//            def hasOnePermissionAtLeast = false
//            def result = node.attributes()["package"] + " contains permission "
//            checkPermissions.each {
//                if (it.hasPermission) {
//                    if (!hasOnePermissionAtLeast) {
//                        hasOnePermissionAtLeast = true
//                    }
//                    result = result + it.permissionName + (checkPermissions.indexOf(it) == checkPermissions.size() - 1 ? "." : ",")
//                }
//            }
//            //至少包含一个权限，则输出日志
//            if (hasOnePermissionAtLeast) {
//                println result
//            }
//        } catch (Exception e) {
//            e.printStackTrace()
//        }
//    }
//}
