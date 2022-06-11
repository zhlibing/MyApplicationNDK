#include <jni.h>
#include <base.h>
#include <jvm.h>
#include <android/log.h>

#define JAVA_CLASS "com/example/myapplication/JNIDynamicLoad"

//
// Created by admin on 2022/4/10.
//
jstring getMessage(JNIEnv *env, jobject jobj) {
    return env->NewStringUTF("this is getMessage");
};

jint plus(JNIEnv *env, jobject jobj, int x, int y) {
    return x + y;
};

static JNINativeMethod gMethods[]{
        {"getNativeString", "()Ljava/lang/String;", (void *) getMessage},
        {"sum",             "(II)I",                (void *) plus}
};

int
registerNativeMethods(JNIEnv *env, const char *name, const JNINativeMethod *methods,
                      jint nMethods) {
    jclass jcls;
    jcls = env->FindClass(name);
    if (jcls == nullptr) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(jcls, methods, nMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT int JNICALL
JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_FALSE;
    }
    registerNativeMethods(env, JAVA_CLASS, gMethods, 2);
//    LOGD("registerNativeMethods");
    return JNI_VERSION_1_6;
}

//extern "C"
//JNIEXPORT jstring JNICALL
//Java_com_example_myapplication_JNIDynamicLoad_getNativeString(JNIEnv *env, jobject thiz) {
//    // TODO: implement getNativeString()
//}
//extern "C"
//JNIEXPORT jint JNICALL
//Java_com_example_myapplication_JNIDynamicLoad_sum(JNIEnv *env, jobject thiz, jint x, jint y) {
//    // TODO: implement sum()
//}