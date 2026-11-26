package com.navi.tool.ui.extra;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import com.navi.tool.utils.ToastHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TextEditorActivity extends androidx.appcompat.app.r {
    public static final String EXTRA_FILE_PATH = "file_path";
    private File currentFile;
    private EditText editText;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFileContent$0(String str) {
        this.editText.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFileContent$1(IOException iOException) {
        ToastHelper.show(this, "读取失败: " + iOException.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFileContent$2() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.currentFile)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        runOnUiThread(new y5(this, sb.toString(), 1));
                        bufferedReader.close();
                        return;
                    } else {
                        sb.append(readLine);
                        sb.append("\n");
                    }
                } finally {
                }
            }
        } catch (IOException e7) {
            runOnUiThread(new z5(this, e7, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveFileContent$3() {
        ToastHelper.show(this, "保存成功");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveFileContent$4(IOException iOException) {
        ToastHelper.show(this, "保存失败: " + iOException.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveFileContent$5(String str) {
        int i7 = 1;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.currentFile)));
            try {
                bufferedWriter.write(str);
                runOnUiThread(new x5(this, i7));
                bufferedWriter.close();
            } finally {
            }
        } catch (IOException e7) {
            runOnUiThread(new z5(this, e7, i7));
        }
    }

    private void loadFileContent() {
        new Thread(new x5(this, 0)).start();
    }

    private void saveFileContent() {
        new Thread(new y5(this, this.editText.getText().toString(), 0)).start();
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(g4.q.activity_text_editor);
        setSupportActionBar((Toolbar) findViewById(g4.p.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().n(true);
        }
        this.editText = (EditText) findViewById(g4.p.edit_text_content);
        String stringExtra = getIntent().getStringExtra(EXTRA_FILE_PATH);
        if (stringExtra == null) {
            ToastHelper.show(this, "未指定文件");
            finish();
            return;
        }
        File file = new File(stringExtra);
        this.currentFile = file;
        if (file.exists()) {
            getSupportActionBar().t(this.currentFile.getName());
            loadFileContent();
        } else {
            ToastHelper.show(this, "文件不存在");
            finish();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(g4.r.text_editor_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        if (menuItem.getItemId() != g4.p.action_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        saveFileContent();
        return true;
    }
}
