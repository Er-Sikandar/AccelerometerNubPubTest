package com.testdemo.apps.common;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

public class NoPasteEditText extends AppCompatEditText {
    private static final String TAG = "NoPasteEditText";

    public NoPasteEditText(Context context) {
        super(context);
        init();
    }

    public NoPasteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoPasteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }
        });
        setLongClickable(false);
        setTextIsSelectable(false);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        if (id == android.R.id.paste || id == android.R.id.pasteAsPlainText ||
                id == android.R.id.copy || id == android.R.id.cut) {
            Log.d(TAG, "Copy/Cut/Paste action detected and blocked");
            return false;
        }
        return super.onTextContextMenuItem(id);
    }

    @Override
    public InputConnection onCreateInputConnection(@NonNull EditorInfo outAttrs) {
        InputConnection baseInputConnection = super.onCreateInputConnection(outAttrs);
        if (baseInputConnection == null) {
            Log.e(TAG, "Base InputConnection is null");
            return null;
        }

        return new InputConnectionWrapper(baseInputConnection, true) {
            @Override
            public boolean commitText(CharSequence text, int newCursorPosition) {
                // Optionally, you can log or restrict input here if needed.
                Log.d(TAG, "commitText: " + text);
                if (text != null && text.length() > 0) {
                    Log.d(TAG, "commitText: Blocking paste action from keyboard");
                    return false;
                }
                return super.commitText(text, newCursorPosition);
            }

            @Override
            public boolean performEditorAction(int editorAction) {
                return super.performEditorAction(editorAction);
            }

            @Override
            public boolean sendKeyEvent(KeyEvent event) {
                return super.sendKeyEvent(event);
            }
            @Override
            public boolean setComposingText(CharSequence text, int newCursorPosition) {
                if (text != null && text.length() > 1) {
                    Log.d(TAG, "setComposingText: Blocking paste action");
                    return false;
                }
                return super.setComposingText(text, newCursorPosition);
            }
        };
    }
}

