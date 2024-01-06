/*
 * redirect.c
 * OutputRedirector-Android - Redirect stdout and stderr to file
 *
 * Copyright (c) 2020, 2024 ViliusSutkus89.com
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

JNIEXPORT void JNICALL
Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirectStdout(JNIEnv *env,
                                                                                 __attribute__((unused)) jclass clazz,
                                                                                 jstring requested_stdout) {
    const char * stdout_c = (*env)->GetStringUTFChars(env, requested_stdout, NULL);
    if (NULL != stdout_c) {
        freopen(stdout_c, "w+", stdout);
        (*env)->ReleaseStringUTFChars(env, requested_stdout, stdout_c);
    }
}

JNIEXPORT void JNICALL
Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirectStderr(JNIEnv *env,
                                                                                 __attribute__((unused)) jclass clazz,
                                                                                 jstring requested_stderr) {
    const char * stderr_c = (*env)->GetStringUTFChars(env, requested_stderr, NULL);
    if (NULL != stderr_c) {
        freopen(stderr_c, "w+", stderr);
        (*env)->ReleaseStringUTFChars(env, requested_stderr, stderr_c);
    }
}

JNIEXPORT void JNICALL
Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirect(JNIEnv *env,
                                                                           jclass clazz,
                                                                           jstring requested_stdout,
                                                                           jstring requested_stderr) {
    Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirectStdout(env, clazz, requested_stdout);
    Java_com_viliussutkus89_android_outputredirector_OutputRedirector_redirectStderr(env, clazz, requested_stderr);
}
