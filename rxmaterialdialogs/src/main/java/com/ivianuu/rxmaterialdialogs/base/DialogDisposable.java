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

package com.ivianuu.rxmaterialdialogs.base;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.MaterialDialog;

import io.reactivex.disposables.Disposable;

/**
 * Dismisses the dialog on dispose
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class DialogDisposable implements Disposable {

    private final MaterialDialog dialog;
    private boolean disposed;

    public DialogDisposable(@NonNull MaterialDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void dispose() {
        if (!disposed) {
            disposed = true;
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
