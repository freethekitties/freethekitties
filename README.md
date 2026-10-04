# freethekitties

ReVanced patches for mobile puzzle games.

## Patches

| Patch | Description |
|---|---|
| **Disable ads** | Prevents the game's ad SDK and its ad networks from starting, loading or showing ads. |
| **Force Facebook web login** | Signs in to Facebook through the browser instead of the Facebook app, whose signing-key check rejects patched apps. |

### Supported apps

| App | Package | Tested versions |
|---|---|---|
| Me****ku | `com.oa***er.me****ku` | 1.19.1 |

## Installation

Everything happens on your phone, no computer needed.

### Before you start: save your progress with Facebook

The patched app can't sign in with Google (see [Known limitations](#known-limitations)), so your cloud save must be reachable through **Facebook**.

- **Already signed in with Facebook?** Nothing to do.
- **Signed in with Google?** In the original app, sign out, then sign in again with Facebook. Your progress is kept. Check that it's still there before moving on.

### Prerequisites

1. **Uninstall Me\*\*\*\*ku** from your phone, after saving your progress as described above. The patched app is signed with a different key, so it can't be installed over the original.
2. **Install [ReVanced Manager](https://revanced.app/download).**
3. **Install [AntiSplit-M](https://github.com/AbdurazaaqMohammed/AntiSplit-M/releases):** open the latest release and tap the `.apk` file under *Assets*.
4. **Download the Me\*\*\*\*ku `.xapk`** from [APKPure](https://apkpure.com/). Prefer a version listed under [Supported apps](#supported-apps): other versions may work, but ReVanced Manager will flag them as untested.

Android asks you to allow *Install unknown apps* the first time an app installs another app (your browser, AntiSplit-M, ReVanced Manager). Allow it when prompted.

### Step 1: Merge the `.xapk`

The `.xapk` is a bundle of several APK files, but ReVanced needs a single APK.

1. Open **AntiSplit-M** and select the downloaded `.xapk`.
2. Merge it and note where the merged `.apk` is saved.

### Step 2: Add the patches to ReVanced Manager

1. Open **ReVanced Manager** and go to the patches sources (the **Patches** tab).
2. Tap **+**, choose **Enter URL**, and paste this URL:
   ```
   https://raw.githubusercontent.com/freethekitties/freethekitties/main/patches-bundle.json
   ```
3. Enable auto-update if offered, so you get new patch versions automatically.

**freethekitties patches** now appears in the list of sources.

### Step 3: Patch and install

1. In the **Apps** tab, choose **Select from storage** and pick the merged `.apk` from step 1.
2. Check that **Disable ads** and **Force Facebook web login** are both selected.
3. Patch, then install the patched app.

### Step 4: Recover your progress

The patched app starts like a fresh install: play the tutorial level, then go back to the main menu as soon as you can and **sign in with Facebook**. Your progress is restored from the cloud.

### Need help?

If you're stuck, give this page's link to an AI assistant and ask it to walk you through the step.

## Known limitations

- **Google sign-in doesn't work** on patched apps: Google checks the APK signature. Use Facebook sign-in instead: switch to Facebook in the original app before patching, so your cloud save carries over (see [Before you start](#before-you-start-save-your-progress-with-facebook)).

## License

GPLv3, see [LICENSE](LICENSE).
