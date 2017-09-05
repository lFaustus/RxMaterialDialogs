/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.rxmaterialdialogscommons.listmaterial;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;

import com.ivianuu.rxmaterialdialogs.base.BaseDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Maybe;

/**
 * Material list dialog builder
 */
public class MaterialListDialogBuilder extends BaseDialogBuilder<MaterialListDialogBuilder> {

    private List<MaterialListItem> items = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public MaterialListDialogBuilder(@NonNull Context context) {
        super(context);
        setThisBuilder(this);
    }

    public MaterialListDialogBuilder layoutManager(@NonNull RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public MaterialListDialogBuilder addItem(@NonNull MaterialListItem item) {
        this.items.add(item);
        return this;
    }

    public MaterialListDialogBuilder addItems(@NonNull MaterialListItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public MaterialListDialogBuilder addItems(@NonNull List<MaterialListItem> items) {
        this.items.addAll(items);
        return this;
    }

    @CheckResult
    @NonNull
    public Maybe<MaterialListDialogEvent> build() {
        return MaterialListDialogMaybe.create(wrappedBuilder, items, layoutManager);
    }
}
