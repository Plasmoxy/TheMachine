
#include <iostream>
#include "Utility.h"

JNIEXPORT jint JNICALL Java_Utility_hello(JNIEnv * env, jobject obj, jint a, jint b) {
	
    return a + b;
    
}