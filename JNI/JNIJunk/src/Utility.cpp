
#include <iostream>
#include "Utility.h"

JNIEXPORT void JNICALL Java_Utility_hello(JNIEnv *, jobject) {
    std::cout << "HELLO WORLD" << std::endl;
}