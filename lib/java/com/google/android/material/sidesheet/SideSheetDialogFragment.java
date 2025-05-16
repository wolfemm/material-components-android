/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.google.android.material.sidesheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * Modal side sheet. This is a version of {@link androidx.fragment.app.DialogFragment} that shows
 * a side sheet using {@link SideSheetDialog} instead of a floating dialog.
 *
 * <p>For more information, see the <a
 * href="https://github.com/material-components/material-components-android/blob/master/docs/components/SideSheet.md">component
 * developer guidance</a> and <a
 * href="https://material.io/components/side-sheets/overview">design guidelines</a>.
 */
public class SideSheetDialogFragment extends AppCompatDialogFragment {

  /**
   * Tracks if we are waiting for a dismissAllowingStateLoss or a regular dismiss once the
   * SideSheet is hidden and onStateChanged() is called.
   */
  private boolean waitingForDismissAllowingStateLoss;

  public SideSheetDialogFragment() {}

  @SuppressLint("ValidFragment")
  public SideSheetDialogFragment(@LayoutRes int contentLayoutId) {
    super(contentLayoutId);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    return new SideSheetDialog(getContext(), getTheme());
  }

  @Override
  public void dismiss() {
    if (!tryDismissWithAnimation(false)) {
      super.dismiss();
    }
  }

  @Override
  public void dismissAllowingStateLoss() {
    if (!tryDismissWithAnimation(true)) {
      super.dismissAllowingStateLoss();
    }
  }

  /**
   * Tries to dismiss the dialog fragment with the bottom sheet animation. Returns true if possible,
   * false otherwise.
   */
  private boolean tryDismissWithAnimation(boolean allowingStateLoss) {
    Dialog baseDialog = getDialog();
    if (baseDialog instanceof SideSheetDialog) {
      SideSheetDialog dialog = (SideSheetDialog) baseDialog;
      SideSheetBehavior<?> behavior = dialog.getBehavior();

      dismissWithAnimation(behavior, allowingStateLoss);
      return true;
    }

    return false;
  }

  private void dismissWithAnimation(
      @NonNull SideSheetBehavior<?> behavior, boolean allowingStateLoss) {
    waitingForDismissAllowingStateLoss = allowingStateLoss;

    if (behavior.getState() == SideSheetBehavior.STATE_HIDDEN) {
      dismissAfterAnimation();
    } else {
      behavior.addCallback(new SideSheetDismissCallback());
      behavior.setState(SideSheetBehavior.STATE_HIDDEN);
    }
  }

  private void dismissAfterAnimation() {
    if (waitingForDismissAllowingStateLoss) {
      super.dismissAllowingStateLoss();
    } else {
      super.dismiss();
    }
  }

  private class SideSheetDismissCallback extends SideSheetCallback {
    @Override
    public void onStateChanged(@NonNull View sideSheet, int newState) {
      if (newState == SideSheetBehavior.STATE_HIDDEN) {
        dismissAfterAnimation();
      }
    }

    @Override
    public void onSlide(@NonNull View sideSheet, float slideOffset) {}
  }
}
