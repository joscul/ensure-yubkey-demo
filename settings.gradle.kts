rootProject.name = "webauthn-server-parent"
include(":webauthn-server-attestation")
include(":webauthn-server-core")
include(":webauthn-server-demo")
include(":yubico-util")
include(":yubico-util-scala")

include(":test-dependent-projects:java-dep-webauthn-server-attestation")
include(":test-dependent-projects:java-dep-webauthn-server-core")
include(":test-dependent-projects:java-dep-webauthn-server-core-and-bouncycastle")
include(":test-dependent-projects:java-dep-yubico-util")
include(":test-platform")
