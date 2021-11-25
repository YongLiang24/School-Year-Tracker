package com.yongliang.schoolyeartracker.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yongliang.schoolyeartracker.Entity.TermEntity;
import com.yongliang.schoolyeartracker.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {


    //inner class
    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termTitle;
        private final TextView termStartDate;
        private final TextView termEndDate;
        private TermViewHolder(View termView){
            super (termView);
            termTitle = termView.findViewById(R.id.termView);
            termStartDate = termView.findViewById(R.id.termView2);
            termEndDate = termView.findViewById(R.id.termView3);

            termView.setOnClickListener(new View.OnClickListener(){
                //individual item click event
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final TermEntity current=mTerms.get(position);
                    //to next screen
                    Intent intent = new Intent(context, CourseActivity.class);
                    //pass extra data to the next screen
                    intent.putExtra("id", current.getId());
                    //intent.putExtra("termName", current.getTermName());
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<TermEntity> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View termView=mInflater.inflate(R.layout.list_term, parent, false);
        return new TermViewHolder(termView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms != null){
            TermEntity current = mTerms.get(position);
            holder.termTitle.setText(current.getTermName());
            holder.termStartDate.setText(current.getStartDate());
            holder.termEndDate.setText(current.getEndDate());
        }
        else{
            holder.termTitle.setText("no term title");
        }

    }

    public void setTerms(List<TermEntity> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }
}
