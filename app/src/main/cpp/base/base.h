//
// Created by admin on 2022/4/10.
//

#ifndef MY_APPLICATIONNDK_BASE_H
#define MY_APPLICATIONNDK_BASE_H

#include <android/log.h>
#include <jni.h>

#define LOG_TAG "TEST_JNI"
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__))

#endif //MY_APPLICATIONNDK_BASE_H
