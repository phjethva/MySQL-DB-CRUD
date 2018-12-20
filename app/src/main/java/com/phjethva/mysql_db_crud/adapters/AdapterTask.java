package com.phjethva.mysql_db_crud.adapters;
/**
 * @author PJET APPS (Pratik Jethva)
 * Check Out My Other Repositories On Github: https://github.com/phjethva
 * Visit My Website: https://www.pjetapps.com
 * Follow My Facebook Page: https://www.facebook.com/pjetapps
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phjethva.mysql_db_crud.R;
import com.phjethva.mysql_db_crud.models.ModelTask;
import com.phjethva.mysql_db_crud.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.TaskViewHolder> {

    public ItemClick click;
    private List<ModelTask> tasks;

    public interface ItemClick {
        void callbackItemClick(ModelTask task, ImageButton imageButtonMenu);
    }

    public AdapterTask(ItemClick click, List<ModelTask> tasks) {
        this.click = click;
        this.tasks = tasks;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, taskDateTime;
        ImageButton imageButtonMenu;

        public TaskViewHolder(View view) {
            super(view);
            taskName = view.findViewById(R.id.textview_task_name);
            taskDateTime = view.findViewById(R.id.textview_task_date_time);
            imageButtonMenu = view.findViewById(R.id.imagebutton_menu);
        }

        public void bind(final ModelTask task, final ItemClick click) {
            imageButtonMenu.setOnClickListener(new MenuButtonClick(task, imageButtonMenu));
        }

    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        ModelTask task = tasks.get(position);
        holder.taskName.setText(task.getTaskName());
        holder.taskDateTime.setText(Utils.formatDateTime(task.getTaskDateTime()));
        holder.bind(task, click);
    }

    public void notifyData(List<ModelTask> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class MenuButtonClick implements View.OnClickListener {
        ModelTask task;
        ImageButton imageButtonMenu;

        public MenuButtonClick(ModelTask task, ImageButton imageButtonMenu) {
            this.task = task;
            this.imageButtonMenu = imageButtonMenu;
        }

        @Override
        public void onClick(View v) {
            if (click != null) {
                click.callbackItemClick(task, imageButtonMenu);
            }
        }
    }

}