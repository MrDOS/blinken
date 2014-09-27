#include <stdint.h>

#include <jni.h>
#include "com_seesideproductions_blinken_controllers_DasController.h"

#include <windows.h>
#include "_DasHard.h"

#define UNIVERSE_SIZE 512
#define MIN(a, b) ((a < b) ? a : b)

HINSTANCE DAS_LIB = NULL;
int lib_refs = 0;

typedef int (*DASHARDCOMMAND)(int, int, void *);
DASHARDCOMMAND DasUsbCommand = NULL;

JNIEXPORT void JNICALL _Java_com_seesideproductions_blinken_controllers_DasController_open(JNIEnv *env, jobject this)
{
    if (DAS_LIB == NULL)
    {
        DAS_LIB = LoadLibrary("DasHard2006.dll");
        DasUsbCommand = (DASHARDCOMMAND) GetProcAddress(DAS_LIB, "DasUsbCommand");

        DasUsbCommand(DHC_INIT, 0, NULL);
    }

    lib_refs++;
}

JNIEXPORT void JNICALL _Java_com_seesideproductions_blinken_controllers_DasController_close(JNIEnv *env, jobject this)
{
    if (--lib_refs == 0)
    {
        DasUsbCommand(DHC_EXIT, 0, NULL);

        FreeLibrary(DAS_LIB);
        DAS_LIB = NULL;
        DasUsbCommand = NULL;
    }
}

JNIEXPORT void JNICALL _Java_com_seesideproductions_blinken_controllers_DasController_set(JNIEnv *env, jobject this, jbyteArray jValues)
{
    /* If, for some reason, we've been sent an array of values smaller than the
     * maximum size of a the DMX512 universe, we want to make sure we know ahead
     * of time so we don't accidentally try to send a bunch of garbage data out
     * to the controller (or worse, segfault). */
    jsize valuec = MIN((*env)->GetArrayLength(env, jValues), UNIVERSE_SIZE);
    jbyte *values = (*env)->GetByteArrayElements(env, jValues, NULL);

    DasUsbCommand(DHC_OPEN, 0, NULL);
    DasUsbCommand(DHC_DMXOUT, valuec, values);
    DasUsbCommand(DHC_CLOSE, 0, NULL);

    (*env)->ReleaseByteArrayElements(env, jValues, values, JNI_ABORT);
}
