# Please read the following before starting
* [Guiding Principles](docs/guiding_principles.md)
* [App Architecture documents](https://alaskaair.sharepoint.com/sites/ASICS/CM/CM/Documentation/Forms/AllItems.aspx?id=%2Fsites%2FASICS%2FCM%2FCM%2FDocumentation%2FCustomer%20Android%20App%2FArchitecture)
* [Code - State of the Union](docs/code_state_of_the_union.md)
* [OS Upgrades](docs/OS_upgrades.md)

# Getting Started
1. Clone this repo
1. Install latest Android Studio
1. Open the project (the folder with gradle file)
1. Setup an Emulator or plug in a real device
1. Run the app

# Subject Matter Experts
* William and Jordan - William has context on the app since the very beginning
* William's involvment (he is not officially part of the team as of January 2020):
    * Review and comment on PRs as needed
    * Consulting for the app. Historical reasons, architectural decisions, etc.
    * Occasionally contribute changes to the app

# Build Pipelines
* Builds are triggered via Azure Devops at https://itsals.visualstudio.com/DefaultCollection/CustomerMobile/_build?definitionId=7211
    * Backup on-prem build pipeline https://itsals.visualstudio.com/DefaultCollection/CustomerMobile/_build?definitionId=2741
* Builds are performed with an Azure agent
    * Backup on-prem physical build box, **seavxandroidr** (William usually keeps this box updated)
        * Currently located on Chris Maher's desk (maybe Nick's desk) in the Hub building
        * [NOT HAPPENING RIGHT NOW]Espresso tests are ran on each build on a connected device, Samsung Galaxy 6+
        * The Azure DevOps agent is installed on C:\agent
        * Agent pool name = Day of Travel
        * Agent is running under an account with elevated permissions: airgroup\mobilebldservtest

## Build Artifact locations
### Microsoft AppCenter (Primary)
* https://appcenter.ms/apps
* For access: ask an app dev to add your alaskaair.com SSO account to the team
### On-Prem (Secondary, VPN required)
* On-Prem QA VM: **\\\Seadvwebalpha01\D\Android**
* PROD apks will be in Android.PROD folder
* Debug builds will be in Android.DEBUG

# App Stores (Google & Amazon)
Google Play Store master account (log in as): google.app.store@alaskaair.com
* There is a shared outlook inbox if you need access to this email address
* As of 7/12/2021: Sharryn is the "owner", because her email address is in the Account Details
* If you need the master account, please reach out to William.

Amazon App Store
* Admins as of 06/08/2022: Sharryn (owner), William, Jordan, Mo, Kaitlyn

# How to create a Release (outdated - needs updating)
1. In develop branch, increment versionCode and version number (version_var in getCustomVersionNumber in build.gradle)
    * Do not include revision number for major and minor releases. (e.g. Use 3.19 instead of 3.19.0)
2. Merge develop to main via PR
3. Tag main with the version number in step 1
4. The PROD apk can be found in the [build artifact locations](#build-artifact-locations)
    * Confirm the prod apk should have "-release" in the filename and NOT "-debug"
5. Verify apk installs on a physical device and regression test app
    * Always do an upgrade test from previous version to current version
6. Upload apk to both stores
