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

package com.google.android.material.color;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.google.android.material.color.DynamicColors.OnAppliedCallback;
import com.google.android.material.color.DynamicColors.Precondition;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/** Wrapper class for specifying dynamic colors options when applying dynamic colors. */
public class DynamicColorsOptions {

  private static final Precondition ALWAYS_ALLOW =
      new Precondition() {
        @Override
        public boolean shouldApplyDynamicColors(@NonNull Activity activity, int theme) {
          return true;
        }
      };

  private static final OnAppliedCallback NO_OP_CALLBACK =
      new OnAppliedCallback() {
        @Override
        public void onApplied(@NonNull Activity activity) {}
      };

  @StyleRes private final int themeOverlay;
  @NonNull private final Precondition precondition;
  @NonNull private final OnAppliedCallback onAppliedCallback;

  private DynamicColorsOptions(Builder builder) {
    this.themeOverlay = builder.themeOverlay;
    this.precondition = builder.precondition;
    this.onAppliedCallback = builder.onAppliedCallback;
  }

  /** Returns the resource ID of the theme overlay that provides dynamic color definition. */
  @StyleRes
  public int getThemeOverlay() {
    return themeOverlay;
  }

  /** Returns the precondition that decides if dynamic colors should be applied. */
  @NonNull
  public Precondition getPrecondition() {
    return precondition;
  }

  /** Returns the callback method after dynamic colors have been applied. */
  @NonNull
  public OnAppliedCallback getOnAppliedCallback() {
    return onAppliedCallback;
  }

  /** Builder class for specifying options when applying dynamic colors. */
  public static class Builder {

    @StyleRes private int themeOverlay;
    @NonNull private Precondition precondition = ALWAYS_ALLOW;
    @NonNull private OnAppliedCallback onAppliedCallback = NO_OP_CALLBACK;

    /** Sets the resource ID of the theme overlay that provides dynamic color definition. */
    @NonNull
    @CanIgnoreReturnValue
    public Builder setThemeOverlay(@StyleRes int themeOverlay) {
      this.themeOverlay = themeOverlay;
      return this;
    }

    /** Sets the precondition that decides if dynamic colors should be applied. */
    @NonNull
    @CanIgnoreReturnValue
    public Builder setPrecondition(@NonNull Precondition precondition) {
      this.precondition = precondition;
      return this;
    }

    /** Sets the callback method for after the dynamic colors have been applied. */
    @NonNull
    @CanIgnoreReturnValue
    public Builder setOnAppliedCallback(@NonNull OnAppliedCallback onAppliedCallback) {
      this.onAppliedCallback = onAppliedCallback;
      return this;
    }

    @NonNull
    public DynamicColorsOptions build() {
      return new DynamicColorsOptions(this);
    }
  }
}
