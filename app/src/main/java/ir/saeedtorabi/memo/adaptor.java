package ir.saeedtorabi.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeed_trb on 6/21/2015.
 */
public class adaptor  extends BaseAdapter{
    private final List<MemoModel> myList;
    private final LayoutInflater layInflat;
    private final Context context;
    private final DataBase DB;

    public adaptor(final Context context, DataBase db) {
        DB = db;
        this.myList = new ArrayList<MemoModel>();
        this.layInflat = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public MemoModel getItem(int i) {
        return myList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder viewholder;
        if(view==null)
        {
            view=layInflat.inflate(R.layout.memo_item,viewGroup,false);
            viewholder=new viewHolder(view);
            view.setTag(viewholder);
        }else {
            viewholder=(viewHolder) view.getTag();
        }
        ImageButton btn=(ImageButton) view.findViewById(R.id.btn_delete_memo);
        btn.setTag(i);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();
                DB.delete( DB , index );
                myList.remove(index);
                notifyDataSetChanged();
            }
        });
        viewholder.titleMemo.setText(myList.get(i).getTitle());
        viewholder.contentMemo.setText(myList.get(i).getCountent());
        return view;
    }
    private class viewHolder
    {
        TextView titleMemo,contentMemo;
        public viewHolder(View view)
        {
            titleMemo  =(TextView) view.findViewById(R.id.title_item_memo);
            contentMemo =(TextView) view.findViewById( R.id.content_item_memo );
        }

    }

    public void updateMyList(List<MemoModel> newList)
    {
        this.myList.clear();
        this.myList.addAll(newList);
        notifyDataSetChanged();
    }

}
