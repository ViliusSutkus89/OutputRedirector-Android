/*
 * redirect.cpp
 * OutputRedirector-Android - Redirect stdout and stderr to file
 *
 * Copyright (c) 2020 Vilius Sutkus'89
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

#include <stdio.h>
#include <jni.h>
#include "CCharGC.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirect(JNIEnv *env, jclass, jstring stdout_, jstring stderr_) {
    CCharGC stdout_c(env, stdout_);
    CCharGC stderr_c(env, stderr_);
    freopen(stdout_c.c_str(), "w+", stdout);
    freopen(stderr_c.c_str(), "w+", stderr);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirectStdout(JNIEnv *env,
                                                                                 jclass,
                                                                                 jstring input) {
    CCharGC input_c(env, input);
    freopen(input_c.c_str(), "w+", stdout);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirectStderr(JNIEnv *env,
                                                                                 jclass,
                                                                                 jstring input) {
    CCharGC input_c(env, input);
    freopen(input_c.c_str(), "w+", stderr);
}
