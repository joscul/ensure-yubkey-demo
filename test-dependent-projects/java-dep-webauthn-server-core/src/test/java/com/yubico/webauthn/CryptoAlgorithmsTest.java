package com.yubico.webauthn;

import static org.junit.Assert.assertEquals;

import COSE.CoseException;
import com.yubico.webauthn.data.AttestationObject;
import com.yubico.webauthn.data.RelyingPartyIdentity;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CryptoAlgorithmsTest {

  private List<Provider> providersBefore;

  @Before
  public void setUp() {
    providersBefore = Stream.of(Security.getProviders()).collect(Collectors.toList());

    // The RelyingParty constructor has the possible side-effect of loading the BouncyCastle
    // provider
    RelyingParty.builder()
        .identity(RelyingPartyIdentity.builder().id("foo").name("foo").build())
        .credentialRepository(Mockito.mock(CredentialRepository.class))
        .build();
  }

  @After
  public void tearDown() {
    for (Provider prov : Security.getProviders()) {
      Security.removeProvider(prov.getName());
    }
    providersBefore.forEach(Security::addProvider);
  }

  @Test
  public void importRsa()
      throws IOException, CoseException, NoSuchAlgorithmException, InvalidKeySpecException {
    PublicKey key =
        WebAuthnCodecs.importCosePublicKey(
            new AttestationObject(
                    RegistrationTestData.Packed$.MODULE$.BasicAttestationRsa().attestationObject())
                .getAuthenticatorData()
                .getAttestedCredentialData()
                .get()
                .getCredentialPublicKey());
    assertEquals(key.getAlgorithm(), "RSA");
  }

  @Test
  public void importEcdsa()
      throws IOException, CoseException, NoSuchAlgorithmException, InvalidKeySpecException {
    PublicKey key =
        WebAuthnCodecs.importCosePublicKey(
            new AttestationObject(
                    RegistrationTestData.Packed$.MODULE$.BasicAttestation().attestationObject())
                .getAuthenticatorData()
                .getAttestedCredentialData()
                .get()
                .getCredentialPublicKey());
    assertEquals(key.getAlgorithm(), "EC");
  }
}
