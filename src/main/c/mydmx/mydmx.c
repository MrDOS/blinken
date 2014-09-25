#include <jni.h>
#include "com_seesideproductions_blinken_controllers_MyDMXController.h"

#include <windows.h>
#include "_DasHard.h"

#define UNIVERSE_SIZE 512
#define MAX(a, b) ((a > b) ? a : b)

JNIEXPORT void JNICALL Java_com_seesideproductions_blinken_controllers_MyDMXController_open(JNIEnv *env, jobject this)
{
    if (DasUsbCommand(DHC_OPEN, 0, NULL) < 0)
    {
        (*env)->ThrowNew(env,
            (*env)->FindClass(env, "com/seesideproductions/blinken/controllers/DMXException"),
            "Could not open interface.");
        return;
    }

    DasUsbCommand(DHC_DMXOUTOFF, 0, NULL);
}

JNIEXPORT void JNICALL Java_com_seesideproductions_blinken_controllers_MyDMXController_close(JNIEnv *env, jobject this)
{
    DasUsbCommand(DHC_CLOSE, 0, NULL);
}

JNIEXPORT void JNICALL Java_com_seesideproductions_blinken_controllers_MyDMXController_set(JNIEnv *env, jobject this, jbyteArray jValues)
{
    /* If, for some reason, we've been sent an array of values smaller than the
     * maximum size of a the DMX512 universe, we want to make sure we know ahead
     * of time so we don't accidentally try to send a bunch of garbage data out
     * to the controller (or worse, segfault). */
    jsize valuec = MAX((*env)->GetArrayLength(env, jValues), UNIVERSE_SIZE);
    jbyte *values = (*env)->GetByteArrayElements(env, jValues, NULL);

    if (DasUsbCommand(DHC_DMXOUT, valuec, values) == DHE_NOTHINGTODO)
    {
        /* The library will notice if a set request doesn't actually change
         * anything and avoid making a round-trip to the controller.
         * Unfortunately, if no USB traffic is observed for 6 seconds, the
         * controller goes into some sort of sleep mode. So if we haven't
         * changed anything, we still need to cause some USB traffic to avoid
         * "losing" the controller.
         *
         * TODO: Does DHC_RESET or DHC_VERSION still cause USB traffic, and does
         * it take less time than a close/open? */
        DasUsbCommand(DHC_CLOSE, 0, NULL);
        DasUsbCommand(DHC_OPEN, 0, NULL);
    }

    (*env)->ReleaseByteArrayElements(env, jValues, values, JNI_ABORT);
}