package app.revanced.patches.facebook.login

import app.revanced.patcher.extensions.getInstruction
import app.revanced.patcher.extensions.instructions
import app.revanced.patcher.extensions.replaceInstruction
import app.revanced.patcher.firstMethod
import app.revanced.patcher.patch.PatchException
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patches.shared.TARGET_PACKAGE
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction

private const val LOGIN_MANAGER = "Lcom/facebook/login/LoginManager;"
private const val LOGIN_BEHAVIOR = "Lcom/facebook/login/LoginBehavior;"

@Suppress("unused")
val forceFacebookWebLoginPatch = bytecodePatch(
    name = "Force Facebook web login",
    description = "Signs in to Facebook through the browser instead of the Facebook app. " +
        "The Facebook app checks the APK signing key hash, which fails on patched (re-signed) apps.",
) {
    compatibleWith(TARGET_PACKAGE("1.19.1"))

    apply {
        firstMethod {
            definingClass == LOGIN_MANAGER && name == "<init>" && parameterTypes.isEmpty()
        }.apply {
            // The constructor sets the default: sget-object vX, LoginBehavior;->NATIVE_WITH_FALLBACK
            val index = instructions.indexOfFirst {
                it.opcode == Opcode.SGET_OBJECT &&
                    (it as ReferenceInstruction).reference.toString() ==
                    "$LOGIN_BEHAVIOR->NATIVE_WITH_FALLBACK:$LOGIN_BEHAVIOR"
            }
            if (index < 0) throw PatchException("Default Facebook LoginBehavior not found in LoginManager")

            val register = getInstruction<OneRegisterInstruction>(index).registerA
            replaceInstruction(index, "sget-object v$register, $LOGIN_BEHAVIOR->WEB_ONLY:$LOGIN_BEHAVIOR")
        }
    }
}
