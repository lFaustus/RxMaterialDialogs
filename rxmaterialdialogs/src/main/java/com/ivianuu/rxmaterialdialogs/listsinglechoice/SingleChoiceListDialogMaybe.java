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

package com.ivianuu.rxmaterialdialogs.listsinglechoice;

import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Single choice list dialog single
 */
class SingleChoiceListDialogMaybe extends DialogMaybe<SingleChoiceListDialogEvent> {

    private int selectedIndex;

    private SingleChoiceListDialogMaybe(MaterialDialog.Builder dialogBuilder, int selectedIndex) {
        super(dialogBuilder);
        this.selectedIndex = selectedIndex;
    }

    static Maybe<SingleChoiceListDialogEvent> create(@NonNull MaterialDialog.Builder dialogBuilder, int selectedIndex) {
        return Maybe.create(new SingleChoiceListDialogMaybe(dialogBuilder, selectedIndex));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<SingleChoiceListDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        dialogBuilder
                .itemsCallbackSingleChoice(selectedIndex, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (!e.isDisposed()) {
                            e.onSuccess(new SingleChoiceListDialogEvent(dialog, itemView, which, text));
                            e.onComplete();
                        }
                        return true;
                    }
                })
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which != DialogAction.POSITIVE && !e.isDisposed()) {
                            e.onComplete();
                        }
                    }
                });
    }
}
