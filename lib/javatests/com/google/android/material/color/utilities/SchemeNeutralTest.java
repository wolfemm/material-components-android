/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.material.color.utilities;

import static com.google.android.material.color.utilities.ArgbSubject.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class SchemeNeutralTest {

  private final MaterialDynamicColors dynamicColors = new MaterialDynamicColors();

  @Test
  public void lightTheme_minContrast_primary() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, -1.0);
    assertThat(dynamicColors.primary().getArgb(scheme)).isSameColorAs(0xff737383);
  }

  @Test
  public void lightTheme_standardContrast_primary() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 0.0);
    assertThat(dynamicColors.primary().getArgb(scheme)).isSameColorAs(0xff5d5d6c);
  }

  @Test
  public void lightTheme_maxContrast_primary() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 1.0);
    assertThat(dynamicColors.primary().getArgb(scheme)).isSameColorAs(0xff21212e);
  }

  @Test
  public void lightTheme_minContrast_primaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, -1.0);
    assertThat(dynamicColors.primaryContainer().getArgb(scheme)).isSameColorAs(0xffe2e1f3);
  }

  @Test
  public void lightTheme_standardContrast_primaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 0.0);
    assertThat(dynamicColors.primaryContainer().getArgb(scheme)).isSameColorAs(0xffe2e1f3);
  }

  @Test
  public void lightTheme_maxContrast_primaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 1.0);
    assertThat(dynamicColors.primaryContainer().getArgb(scheme)).isSameColorAs(0xff414250);
  }

  @Test
  public void lightTheme_minContrast_onPrimaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, -1.0);
    assertThat(dynamicColors.onPrimaryContainer().getArgb(scheme)).isSameColorAs(0xff636372);
  }

  @Test
  public void lightTheme_standardContrast_onPrimaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 0.0);
    assertThat(dynamicColors.onPrimaryContainer().getArgb(scheme)).isSameColorAs(0xff1a1b27);
  }

  @Test
  public void lightTheme_maxContrast_onPrimaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 1.0);
    assertThat(dynamicColors.onPrimaryContainer().getArgb(scheme)).isSameColorAs(0xffd9d8ea);
  }

  @Test
  public void lightTheme_minContrast_surface() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, -1.0);
    assertThat(dynamicColors.surface().getArgb(scheme)).isSameColorAs(0xfffcf8fa);
  }

  @Test
  public void lightTheme_standardContrast_surface() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 0.0);
    assertThat(dynamicColors.surface().getArgb(scheme)).isSameColorAs(0xfffcf8fa);
  }

  @Test
  public void lightTheme_maxContrast_surface() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), false, 1.0);
    assertThat(dynamicColors.surface().getArgb(scheme)).isSameColorAs(0xfffcf8fa);
  }

  @Test
  public void darkTheme_minContrast_primary() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, -1.0);
    assertThat(dynamicColors.primary().getArgb(scheme)).isSameColorAs(0xff737383);
  }

  @Test
  public void darkTheme_standardContrast_primary() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 0.0);
    assertThat(dynamicColors.primary().getArgb(scheme)).isSameColorAs(0xffc6c5d6);
  }

  @Test
  public void darkTheme_maxContrast_primary() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 1.0);
    assertThat(dynamicColors.primary().getArgb(scheme)).isSameColorAs(0xfff6f4ff);
  }

  @Test
  public void darkTheme_minContrast_primaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, -1.0);
    assertThat(dynamicColors.primaryContainer().getArgb(scheme)).isSameColorAs(0xff454654);
  }

  @Test
  public void darkTheme_standardContrast_primaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 0.0);
    assertThat(dynamicColors.primaryContainer().getArgb(scheme)).isSameColorAs(0xff454654);
  }

  @Test
  public void darkTheme_maxContrast_primaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 1.0);
    assertThat(dynamicColors.primaryContainer().getArgb(scheme)).isSameColorAs(0xffcac9da);
  }

  @Test
  public void darkTheme_minContrast_onPrimaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, -1.0);
    assertThat(dynamicColors.onPrimaryContainer().getArgb(scheme)).isSameColorAs(0xffb5b3c4);
  }

  @Test
  public void darkTheme_standardContrast_onPrimaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 0.0);
    assertThat(dynamicColors.onPrimaryContainer().getArgb(scheme)).isSameColorAs(0xffe2e1f3);
  }

  @Test
  public void darkTheme_maxContrast_onPrimaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 1.0);
    assertThat(dynamicColors.onPrimaryContainer().getArgb(scheme)).isSameColorAs(0xff373846);
  }

  @Test
  public void darkTheme_minContrast_onTertiaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, -1.0);
    assertThat(dynamicColors.onTertiaryContainer().getArgb(scheme)).isSameColorAs(0xffb3b3cb);
  }

  @Test
  public void darkTheme_standardContrast_onTertiaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 0.0);
    assertThat(dynamicColors.onTertiaryContainer().getArgb(scheme)).isSameColorAs(0xffe1e0f9);
  }

  @Test
  public void darkTheme_maxContrast_onTertiaryContainer() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 1.0);
    assertThat(dynamicColors.onTertiaryContainer().getArgb(scheme)).isSameColorAs(0xff37374b);
  }

  @Test
  public void darkTheme_minContrast_surface() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, -1.0);
    assertThat(dynamicColors.surface().getArgb(scheme)).isSameColorAs(0xff131315);
  }

  @Test
  public void darkTheme_standardContrast_surface() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 0.0);
    assertThat(dynamicColors.surface().getArgb(scheme)).isSameColorAs(0xff131315);
  }

  @Test
  public void darkTheme_maxContrast_surface() {
    SchemeNeutral scheme = new SchemeNeutral(Hct.fromInt(0xff0000ff), true, 1.0);
    assertThat(dynamicColors.surface().getArgb(scheme)).isSameColorAs(0xff131315);
  }
}
