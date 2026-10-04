# freethekitties

ReVanced patches for mobile puzzle games.

## Patches

| Patch | Description |
|---|---|
| **Disable ads** | Prevents the game's ad SDK and its ad networks from starting, loading or showing ads. Rewarded-ad buttons report that no ad is available. |
| **Force Facebook web login** | Signs in to Facebook through the browser instead of the Facebook app, whose signing-key check rejects patched apps. |

### Supported apps

| App | Package | Tested versions |
|---|---|---|
| Me****ku | `com.oa***er.me****ku` | 1.19.1 |

## Usage

1. Get the app's APK. Most downloads are split bundles (`.xapk`, `.apks`, `.apkm`): merge them into a single APK first, for example with [AntiSplit-M](https://github.com/AbdurazaaqMohammed/AntiSplit-M) on Android or [APKEditor](https://github.com/REAndroid/APKEditor) on a computer (`java -jar APKEditor.jar m -i bundle.xapk -o merged.apk`).
2. In ReVanced Manager, add a remote patches source with this URL:
   `https://raw.githubusercontent.com/freethekitties/freethekitties/main/patches-bundle.json`
3. Select the merged APK, select the patches and patch.
4. Uninstall the original app first: the patched app is signed with a different key and can't be installed over it.

With ReVanced CLI:

```
java -jar revanced-cli.jar patch -p patches-<version>.rvp -s patches-<version>.rvp.asc -k public-key.asc -a <attestation> -r freethekitties/freethekitties merged.apk
```

Releases are signed with the GPG key in [public-key.asc](public-key.asc)
(fingerprint `9DC1 64D4 4468 9D06 F495  162E D9FB 0C9E A278 1EEC`).

## Known limitations

- **Google sign-in doesn't work** on patched apps: Google checks the APK signature. Use Facebook sign-in instead; you can link Facebook to an existing account from the original app first, so your cloud save carries over.
- Rewarded-ad features (extra hints, lives, …) are unavailable.

## Building

Requires a GitHub token with the `read:packages` scope in `~/.gradle/gradle.properties`:

```
githubPackagesUsername=<GitHub username>
githubPackagesPassword=<token>
```

Then run `./gradlew build`. The patches are in `patches/build/libs/`.

## License

GPLv3, see [LICENSE](LICENSE).
