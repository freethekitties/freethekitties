package app.revanced.patches.shared

import java.util.Base64

// App and SDK identifiers are stored Base64-encoded so they don't appear in plain text in the source.
private fun decode(value: String) = String(Base64.getDecoder().decode(value))

internal val TARGET_PACKAGE = decode("Y29tLm9ha2V2ZXIubWVvd2Rva3U=")

// Ad SDK facade that every ad request goes through.
internal val AD_FACADE = decode("TGNvbS9tZWV2aWkvYWRzZGsvTWVldmlpQWQ7")

// Callback interface passed to the facade's init method.
internal val AD_INIT_LISTENER = decode("TGNvbS9tZWV2aWkvYWRzZGsvY29tbW9uL0lJbml0TGlzdGVuZXI7")

// Godot bridge; creates a banner container view before asking the SDK for a banner.
internal val GODOT_AD_BRIDGE = decode("TGNvbS9sZWFybmluZ3MvZ29kb3QvdW5pa2l0L21vZHVsZS9BZE1vZHVsZUJyaWRnZTs=")
