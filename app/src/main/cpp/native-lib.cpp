#include <jni.h>
#include <string>
//#include "People/People.h"
#include <People.h>
#include <base.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */, jint i) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_getStingFromPeople(JNIEnv *env, jobject thiz,
                                                               jstring s, jobject javapeople) {
    People people;
    const char *str = env->GetStringUTFChars(s, 0);
    env->ReleaseStringUTFChars(s, str);
    jclass jls = env->GetObjectClass(javapeople);
    jfieldID fid = env->GetFieldID(jls, "name", "Ljava/lang/String;");
    jmethodID mid = env->GetMethodID(jls, "setSexMethod",
                                     "(Ljava/lang/String;)Ljava/lang/String;");
    jstring jstr = env->NewStringUTF("王五");
    env->SetObjectField(javapeople, fid, jstr);
    jstring sex = env->NewStringUTF("sssssss");
    env->CallObjectMethod(javapeople, mid, sex);
    LOGD("Java_com_example_myapplication_MainActivity_getStingFromPeople");
    return env->NewStringUTF((people.getString() + str).c_str());
}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_myapplication_MainActivity_invokeMenCons(JNIEnv *env, jobject thiz) {
    jclass jcls = env->FindClass("com/example/myapplication/Men");
    jmethodID mid = env->GetMethodID(jcls, "<init>", "(Ljava/lang/String;)V");
    jstring height = env->NewStringUTF("100");
    return env->NewObject(jcls, mid, height);
}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_myapplication_MainActivity_allocObjectCons(JNIEnv *env, jobject thiz) {
    jclass jcls = env->FindClass("com/example/myapplication/Men");
    jmethodID mid = env->GetMethodID(jcls, "<init>", "(Ljava/lang/String;)V");
    jstring height = env->NewStringUTF("200");
    jobject men = env->AllocObject(jcls);
    env->CallNonvirtualVoidMethod(men, jcls, mid, height);
    return men;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_myapplication_MainActivity_nativeCallback(JNIEnv *env, jobject thiz,
                                                           jobject i_callback_method) {
    jclass jcls = env->FindClass("com/example/myapplication/ICallbackMethod");
    jmethodID mid = env->GetMethodID(jcls, "callback", "()V");
    env->CallVoidMethod(i_callback_method, mid);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_myapplication_MainActivity_nativeThreadCallback(JNIEnv *env, jobject thiz,
                                                                 jobject i_callback_method) {
    jclass jcls = env->FindClass("com/example/myapplication/ICallbackMethod");
    jmethodID mid = env->GetMethodID(jcls, "callback", "()V");
//    jmethodID mid = env->GetStaticMethodID(jcls, "callback", "()V");
    env->CallVoidMethod(i_callback_method, mid);
    /**
     * 记得回收
     */
    env->DeleteLocalRef(jcls);
}