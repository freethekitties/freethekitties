package app.revanced.patches.ads

import app.revanced.patcher.extensions.addInstructions
import app.revanced.patcher.firstMethod
import app.revanced.patcher.firstMethodOrNull
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patches.shared.AD_FACADE
import app.revanced.patches.shared.AD_INIT_LISTENER
import app.revanced.patches.shared.GODOT_AD_BRIDGE
import app.revanced.patches.shared.TARGET_PACKAGE

private val voidMethods = listOf(
    "load",
    "show",
    "showBanner",
    "showNative",
    "updateAdConfig",
    "destroy",
    "pause",
    "resume",
    "muteAd",
    "setBannerSize",
    "setLayoutResId",
    "setADListener",
)

@Suppress("unused")
val disableAdsPatch = bytecodePatch(
    name = "Disable ads",
    description = "Prevents the game's ad SDK and its ad networks from starting, loading or showing ads. " +
        "Rewarded-ad buttons will report that no ad is available.",
) {
    compatibleWith(TARGET_PACKAGE("1.19.1"))

    apply {
        // Never start the ad SDKs, but report success so the game does not wait for them.
        firstMethod { definingClass == AD_FACADE && name == "init" }.addInstructions(
            0,
            """
                if-eqz p1, :noads_skip
                invoke-interface {p1}, $AD_INIT_LISTENER->onSuccess()V
                :noads_skip
                return-void
            """,
        )

        // No ad is ever ready or valid.
        listOf("isReady", "isValid").forEach { methodName ->
            firstMethod { definingClass == AD_FACADE && name == methodName }.addInstructions(
                0,
                """
                    const/4 p0, 0x0
                    return p0
                """,
            )
        }

        firstMethod { definingClass == AD_FACADE && name == "getValidAdInfo" }.addInstructions(
            0,
            """
                const/4 p0, 0x0
                return-object p0
            """,
        )

        // Loading, showing and every other call into the (never initialized) SDK become no-ops.
        voidMethods.forEach { methodName ->
            firstMethod { definingClass == AD_FACADE && name == methodName }.addInstructions(0, "return-void")
        }

        // Godot builds only: don't add an empty banner container on top of the game.
        firstMethodOrNull {
            definingClass == GODOT_AD_BRIDGE && name == "showBanner" && returnType == "V"
        }?.addInstructions(0, "return-void")
    }
}
