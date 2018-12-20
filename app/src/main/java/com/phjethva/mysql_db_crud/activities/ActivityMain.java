package com.phjethva.mysql_db_crud.activities;
/**
 * @author PJET APPS (Pratik Jethva)
 * Check Out My Other Repositories On Github: https://github.com/phjethva
 * Visit My Website: https://www.pjetapps.com
 * Follow My Facebook Page: https://www.facebook.com/pjetapps
 */

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phjethva.mysql_db_crud.R;
import com.phjethva.mysql_db_crud.adapters.AdapterTask;
import com.phjethva.mysql_db_crud.db.DBHelper;
import com.phjethva.mysql_db_crud.models.ModelTask;
import com.phjethva.mysql_db_crud.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements
        View.OnClickListener, AdapterTask.ItemClick {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private Button btnAddNewTsk;
    private List<ModelTask> tasks = new ArrayList<>();
    private AdapterTask adapterTask;
    private RecyclerView recyclerViewTask;

    private final static int INSERT_TASK = 1;
    private final static int UPDATE_TASK = 2;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
        setClickListen();

        dbHelper = new DBHelper(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewTask.setLayoutManager(layoutManager);

        tasks = dbHelper.readAllTask();
        adapterTask = new AdapterTask(this, tasks);
        recyclerViewTask.setAdapter(adapterTask);

        readDataBase();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add_new_task:
                dialogTaskAddNew(INSERT_TASK, 0, "");
                break;
        }
    }

    private void setView() {
        btnAddNewTsk = findViewById(R.id.btn_add_new_task);
        recyclerViewTask = findViewById(R.id.recycle_view_task);
    }

    private void setClickListen() {
        btnAddNewTsk.setOnClickListener(this);
    }

    private void readDataBase() {
        tasks = dbHelper.readAllTask();
        adapterTask.notifyData(tasks);
    }

    private void dialogTaskInfo(final ModelTask task) {
        final Dialog dialog = new Dialog(this, R.style.DialogFullScreen);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_task_info);

        final TextView tvInfoDate = dialog.findViewById(R.id.tv_info_date);
        final TextView tvInfoName = dialog.findViewById(R.id.tv_info_name);
        tvInfoDate.setText("Date: " + Utils.formatDateTime(task.getTaskDateTime()));
        tvInfoName.setText(task.getTaskName());
        Button btnInfoClose = dialog.findViewById(R.id.btn_info_close);
        btnInfoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        dialog.getWindow().setAttributes(lp);

    }

    private void dialogTaskAddNew(final int type, final int id, final String msg) {
        final Dialog dialog = new Dialog(this, R.style.DialogFullScreen);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_task_add_new);

        final EditText etAddNewName = dialog.findViewById(R.id.et_add_new_name);
        Button btnAddNewClose = dialog.findViewById(R.id.btn_add_new_close);
        Button btnAddNewAdd = dialog.findViewById(R.id.btn_add_new_add);

        if (type == UPDATE_TASK) {
            etAddNewName.setText(msg);
            btnAddNewAdd.setText(getString(R.string.update));
        } else {
            etAddNewName.setText("");
            btnAddNewAdd.setText(getString(R.string.add));
        }

        btnAddNewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == UPDATE_TASK) {
                    String message = etAddNewName.getText().toString().trim();
                    dbHelper.updateTask(id, message);
                    readDataBase();
                } else if (type == INSERT_TASK) {
                    String message = etAddNewName.getText().toString().trim();
                    dbHelper.createTask(message);
                    readDataBase();
                }
                dialog.dismiss();
            }
        });

        btnAddNewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        dialog.getWindow().setAttributes(lp);

    }

    @Override
    public void callbackItemClick(final ModelTask task, ImageButton imageButtonMenu) {
        PopupMenu popup = new PopupMenu(ActivityMain.this, imageButtonMenu);
        popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.info:
                        dialogTaskInfo(task);
                        break;
                    case R.id.update:
                        dialogTaskAddNew(UPDATE_TASK, task.getId(), task.getTaskName());
                        break;
                    case R.id.delete:
                        dbHelper.deleteTask(task.getId());
                        readDataBase();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

}