package com.phjethva.mysql_db_crud.activities;
/**
 * @author PJET APPS (Pratik Jethva)
 * Check Out My Other Repositories On Github: https://github.com/phjethva
 * Visit My Website: https://www.pjetapps.com
 * Follow My Facebook Page: https://www.facebook.com/pjetapps
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.phjethva.mysql_db_crud.R;
import com.phjethva.mysql_db_crud.adapters.AdapterTask;
import com.phjethva.mysql_db_crud.models.ModelTask;
import com.phjethva.mysql_db_crud.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements AdapterTask.ItemClick {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private List<ModelTask> tasks = new ArrayList<>();
    private AdapterTask adapterTask;
    private RecyclerView recyclerViewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setView();
        setClickListen();

        adapterTask = new AdapterTask(tasks, ActivityMain.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewTask.setLayoutManager(layoutManager);
        recyclerViewTask.setAdapter(adapterTask);

        readDataBase();

    }

    private void setView() {
        recyclerViewTask = findViewById(R.id.recycle_view_task);
    }

    private void setClickListen() {

    }

    private void readDataBase() {
        for (int i = 0; i < 5; i++) {
            ModelTask task = new ModelTask();
            task.setId(i);
            task.setTaskName("ABC");
            task.setTaskDateTime(Utils.formatDateTime("21/11/2018 10:30:50"));
            tasks.add(task);
        }
        adapterTask.notifyDataSetChanged();
    }

    @Override
    public void callbackItemClick(final ModelTask task, ImageButton imageButtonMenu) {
        PopupMenu popup = new PopupMenu(ActivityMain.this, imageButtonMenu);
        popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        ActivityMain.this, "You Clicked : " + task.getId() + " " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popup.show();
    }

}