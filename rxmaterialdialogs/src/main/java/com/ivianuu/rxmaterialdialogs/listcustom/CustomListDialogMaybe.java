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

package com.ivianuu.rxmaterialdialogs.listcustom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Material simple list dialog observable
 */
class CustomListDialogMaybe<Item extends CustomListItem> extends DialogMaybe<CustomListDialogEvent<Item>> {

    private final List<Item> items;
    private final RecyclerView.LayoutManager layoutManager;

    private CustomListDialogMaybe(MaterialDialog.Builder dialogBuilder,
                                    List<Item> items,
                                    RecyclerView.LayoutManager layoutManager) {
        super(dialogBuilder);
        this.items = items;
        this.layoutManager = layoutManager;
    }

    static <Item extends CustomListItem> Maybe<CustomListDialogEvent<Item>>
    create(@NonNull MaterialDialog.Builder builder, @NonNull List<Item> items,
           @NonNull RecyclerView.LayoutManager layoutManager) {
        return Maybe.create(new CustomListDialogMaybe<>(builder, items, layoutManager));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<CustomListDialogEvent<Item>> e,
                              @NonNull MaterialDialog.Builder dialogBuilder) {
        // create adapter
        CustomListAdapter<Item> adapter = new CustomListAdapter<>(items, (dialog, index, item) -> {
            e.onSuccess(new CustomListDialogEvent<>(dialog, index, item));
            e.onComplete();
        });

        // set adapter
        dialogBuilder
                .adapter(adapter, layoutManager)
                .onAny((__, ___) -> {
                    e.onComplete();
                });
    }
}
