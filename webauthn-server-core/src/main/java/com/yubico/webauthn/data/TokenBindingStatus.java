// Copyright (c) 2018, Yubico AB
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.yubico.webauthn.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Indicators of whether a {@link TokenBindingInfo}'s {@link TokenBindingInfo#getId() id} member is
 * present and, if not, whether the client supports token binding.
 *
 * @see <a
 *     href="https://www.w3.org/TR/2021/REC-webauthn-2-20210408/#enumdef-tokenbindingstatus">enum
 *     TokenBindingStatus</a>
 * @see TokenBindingInfo
 */
@AllArgsConstructor
public enum TokenBindingStatus {

  /**
   * Indicates token binding was used when communicating with the Relying Party. In this case, the
   * {@link TokenBindingInfo#getId()} member MUST be present.
   */
  PRESENT("present"),

  /**
   * Indicates the client supports token binding, but it was not negotiated when communicating with
   * the Relying Party.
   */
  SUPPORTED("supported");

  @JsonValue @Getter @NonNull private final String value;

  private static Optional<TokenBindingStatus> fromString(@NonNull String value) {
    return Arrays.stream(values()).filter(v -> v.value.equals(value)).findAny();
  }

  @JsonCreator
  static TokenBindingStatus fromJsonString(@NonNull String value) {
    return fromString(value)
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format(
                        "Unknown %s value: %s", TokenBindingStatus.class.getSimpleName(), value)));
  }
}
