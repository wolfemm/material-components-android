/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.material.carousel;

import com.google.android.material.test.R;

import static com.google.android.material.carousel.CarouselHelper.createCarouselWithWidth;
import static com.google.android.material.carousel.CarouselHelper.createViewWithSize;
import static com.google.common.truth.Truth.assertThat;

import android.view.View;
import androidx.test.core.app.ApplicationProvider;
import com.google.android.material.carousel.KeylineState.Keyline;
import com.google.common.collect.Iterables;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/** Tests for {@link MultiBrowseCarouselStrategy}. */
@RunWith(RobolectricTestRunner.class)
public class MultiBrowseCarouselStrategyTest {

  @Test
  public void testOnFirstItemMeasuredWithMargins_createsKeylineStateWithCorrectItemSize() {
    MultiBrowseCarouselStrategy config = new MultiBrowseCarouselStrategy();
    View view = createViewWithSize(ApplicationProvider.getApplicationContext(), 200, 200);

    KeylineState keylineState =
        config.onFirstChildMeasuredWithMargins(createCarouselWithWidth(584), view);
    assertThat(keylineState.getItemSize()).isEqualTo(200F);
  }

  @Test
  public void testItemLargerThanContainer_resizesToFit() {
    MultiBrowseCarouselStrategy config = new MultiBrowseCarouselStrategy();
    View view = createViewWithSize(ApplicationProvider.getApplicationContext(), 400, 400);

    KeylineState keylineState =
        config.onFirstChildMeasuredWithMargins(createCarouselWithWidth(100), view);
    assertThat(keylineState.getItemSize()).isAtMost(100F);
  }

  @Test
  public void testItemLargerThanContainerSize_defaultsToOneLargeOneSmall() {
    Carousel carousel = createCarouselWithWidth(100);
    MultiBrowseCarouselStrategy config = new MultiBrowseCarouselStrategy();
    View view = createViewWithSize(ApplicationProvider.getApplicationContext(), 400, 400);

    KeylineState keylineState = config.onFirstChildMeasuredWithMargins(carousel, view);
    float minSmallItemSize =
        view.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);

    // A fullscreen layout should be [xSmall-large-small-xSmall] where the xSmall items are
    // outside the bounds of the carousel container and the large center item takes up the
    // containers full width.
    assertThat(keylineState.getKeylines()).hasSize(4);
    assertThat(keylineState.getKeylines().get(0).locOffset).isLessThan(0F);
    assertThat(Iterables.getLast(keylineState.getKeylines()).locOffset)
        .isGreaterThan((float) carousel.getContainerWidth());
    assertThat(keylineState.getKeylines().get(1).mask).isEqualTo(0F);
    assertThat(keylineState.getKeylines().get(2).maskedItemSize).isEqualTo(minSmallItemSize);
  }

  @Test
  public void testKnownArrangementWithMediumItem_correctlyCalculatesKeylineLocations() {
    float[] locOffsets = new float[] {-.5F, 100F, 300F, 464F, 556F, 584.5F};

    MultiBrowseCarouselStrategy config = new MultiBrowseCarouselStrategy();
    View view = createViewWithSize(ApplicationProvider.getApplicationContext(), 200, 200);

    List<Keyline> keylines =
        config.onFirstChildMeasuredWithMargins(createCarouselWithWidth(584), view).getKeylines();
    for (int i = 0; i < keylines.size(); i++) {
      assertThat(keylines.get(i).locOffset).isEqualTo(locOffsets[i]);
    }
  }

  @Test
  public void testKnownArrangementWithoutMediumItem_correctlyCalculatesKeylineLocations() {
    float[] locOffsets = new float[] {-.5F, 100F, 300F, 428F, 456.5F};

    MultiBrowseCarouselStrategy config = new MultiBrowseCarouselStrategy();
    View view = createViewWithSize(ApplicationProvider.getApplicationContext(), 200, 200);

    List<Keyline> keylines =
        config.onFirstChildMeasuredWithMargins(createCarouselWithWidth(456), view).getKeylines();
    for (int i = 0; i < keylines.size(); i++) {
      assertThat(keylines.get(i).locOffset).isEqualTo(locOffsets[i]);
    }
  }

  @Test
  public void testArrangementFit_onlyAdjustsMediumSizeUp() {
    float largeSize = 56F * 3F;
    float smallSize = 56F;
    float mediumSize = (largeSize + smallSize) / 2F;
    float maxMediumAdjustment = mediumSize * .1F;
    // Create a carousel that is larger than 1 of each items added together but within the range of
    // the medium item being able to flex to fit the space.
    int carouselSize = (int) (largeSize + mediumSize + smallSize + maxMediumAdjustment);

    MultiBrowseCarouselStrategy strategy = new MultiBrowseCarouselStrategy();
    View view =
        createViewWithSize(
            ApplicationProvider.getApplicationContext(), (int) largeSize, (int) largeSize);
    KeylineState keylineState =
        strategy.onFirstChildMeasuredWithMargins(createCarouselWithWidth(carouselSize), view);

    // Large and small items should not be adjusted in size by the strategy
    assertThat(keylineState.getKeylines().get(1).maskedItemSize).isEqualTo(largeSize);
    assertThat(keylineState.getKeylines().get(3).maskedItemSize).isEqualTo(smallSize);
    // The medium item should use its flex to fit the arrangement
    assertThat(keylineState.getKeylines().get(2).maskedItemSize).isGreaterThan(mediumSize);
  }

  @Test
  public void testArrangementFit_onlyAdjustsMediumSizeDown() {
    float largeSize = 40F * 3F;
    float smallSize = 40F;
    float mediumSize = (largeSize + smallSize) / 2F;
    float maxMediumAdjustment = mediumSize * .1F;
    int carouselSize = (int) (largeSize + mediumSize + smallSize - maxMediumAdjustment);

    MultiBrowseCarouselStrategy strategy = new MultiBrowseCarouselStrategy();
    View view =
        createViewWithSize(
            ApplicationProvider.getApplicationContext(), (int) largeSize, (int) largeSize);
    KeylineState keylineState =
        strategy.onFirstChildMeasuredWithMargins(createCarouselWithWidth(carouselSize), view);

    // Large and small items should not be adjusted in size by the strategy
    assertThat(keylineState.getKeylines().get(1).maskedItemSize).isEqualTo(largeSize);
    assertThat(keylineState.getKeylines().get(3).maskedItemSize).isEqualTo(smallSize);
    // The medium item should use its flex to fit the arrangement
    assertThat(keylineState.getKeylines().get(2).maskedItemSize).isLessThan(mediumSize);
  }

  @Test
  public void testArrangementFit_onlyAdjustsSmallSizeDown() {
    float largeSize = 56F * 3;
    float smallSize = 56F;
    float mediumSize = (largeSize + smallSize) / 2F;

    View view =
        createViewWithSize(
            ApplicationProvider.getApplicationContext(), (int) largeSize, (int) largeSize);
    float minSmallSize = view.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);
    int carouselSize = (int) (largeSize + mediumSize + minSmallSize);

    MultiBrowseCarouselStrategy strategy = new MultiBrowseCarouselStrategy();
    KeylineState keylineState =
        strategy.onFirstChildMeasuredWithMargins(createCarouselWithWidth(carouselSize), view);

    // Large items should not change
    assertThat(keylineState.getKeylines().get(1).maskedItemSize).isEqualTo(largeSize);
    // Small items should be adjusted to the small size
    assertThat(keylineState.getKeylines().get(3).maskedItemSize).isEqualTo(minSmallSize);
  }

  @Test
  public void testArrangementFit_onlyAdjustsSmallSizeUp() {
    float largeSize = 40F * 3;
    float smallSize = 40F;
    float mediumSize = (largeSize + smallSize) / 2F;

    View view =
        createViewWithSize(
            ApplicationProvider.getApplicationContext(), (int) largeSize, (int) largeSize);
    float maxSmallSize =
        view.getResources().getDimension(R.dimen.m3_carousel_small_item_size_max);
    int carouselSize = (int) (largeSize + mediumSize + maxSmallSize);

    MultiBrowseCarouselStrategy strategy = new MultiBrowseCarouselStrategy();
    KeylineState keylineState =
        strategy.onFirstChildMeasuredWithMargins(createCarouselWithWidth(carouselSize), view);

    // Large items should not change
    assertThat(keylineState.getKeylines().get(1).maskedItemSize).isEqualTo(largeSize);
    // Small items should be adjusted to the small size
    assertThat(keylineState.getKeylines().get(3).maskedItemSize).isEqualTo(maxSmallSize);
  }
}
