package com.example.aia;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.media.Image;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>  {

    private ArrayList<Dictionary> mList;
    private Context mContext;



    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener { // 1. 리스너 추가

        protected TextView mId;
        protected TextView mEnglish;
        protected TextView mKorean;
        protected ImageView mImage;


        public CustomViewHolder(View view) {
            super(view);

            this.mId = (TextView) view.findViewById(R.id.textview_recyclerview_id);
            this.mEnglish = (TextView) view.findViewById(R.id.textview_recyclerview_english);
            this.mKorean = (TextView) view.findViewById(R.id.textview_recyclerview_korean);
            this.mImage = (ImageView) view.findViewById(R.id.imageView);

            view.setOnCreateContextMenuListener(this); //2. 리스너 등록
        }




        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가U

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "Delete");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        // 4. 캔텍스트 메뉴 클릭시 동작을 설정
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case 1001:

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                        final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
                        final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);

                        editTextID.setText(mList.get(getAdapterPosition()).getId());
                        editTextEnglish.setText(mList.get(getAdapterPosition()).getEnglish());
                        editTextKorean.setText(mList.get(getAdapterPosition()).getKorean());


                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strEnglish = editTextEnglish.getText().toString();
                                String strKorean = editTextKorean.getText().toString();

                                Dictionary dict = new Dictionary(strID, strEnglish, strKorean );

                                mList.set(getAdapterPosition(), dict);
                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss();
                            }
                        });

                        dialog.show();

                        break;

                    case 1002:

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        break;

                }
                return true;
            }
        };


    }



    public CustomAdapter(Context context, ArrayList<Dictionary> list) {
        mList = list;
        mContext = context;

    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.mId.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.mEnglish.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        viewholder.mKorean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.mImage.setImageResource(R.drawable.anonymous);

        viewholder.mKorean.bringToFront();
        viewholder.mEnglish.bringToFront();
        viewholder.mId.bringToFront();
        viewholder.mImage.bringToFront();

        viewholder.mId.setTextColor(Color.BLACK);
        viewholder.mEnglish.setTextColor(Color.BLACK);
        viewholder.mKorean.setTextColor(Color.BLACK);

        viewholder.mId.setGravity(Gravity.LEFT);
        viewholder.mEnglish.setGravity(Gravity.RIGHT);
        viewholder.mKorean.setGravity(Gravity.LEFT);

        viewholder.mId.setText(mList.get(position).getId());
        viewholder.mEnglish.setText(mList.get(position).getEnglish());
        viewholder.mKorean.setText(mList.get(position).getKorean());

    }


    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        } else {
            return 0;
        }
    }
}