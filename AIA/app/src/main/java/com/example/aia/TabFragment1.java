package com.example.aia;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class TabFragment1 extends Fragment{
    private static String TAG = "recyclerview_example";

    ArrayList<Dictionary> mArrayList;
    CustomAdapter mAdapter;
    RecyclerView mRecyclerView;
    ArrayList<Dictionary> savedList;
    ArrayList<Dictionary> savedList2;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        readLogFile();

        savedList = new ArrayList<>();
        if (savedList2 != null) {
            savedList = savedList2;
        }
        else {
        }

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_main_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mArrayList = savedList;


        mAdapter = new CustomAdapter(getActivity(), mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        Button buttonInsert = (Button)rootView.findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = LayoutInflater.from(getActivity())
                        .inflate(R.layout.edit_box, null, false);
                builder.setView(view);
                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
                final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);

                ButtonSubmit.setText("Insert");


                final AlertDialog dialog = builder.create();
                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String strID = editTextID.getText().toString();
                        String strEnglish = editTextEnglish.getText().toString();
                        String strKorean = editTextKorean.getText().toString();

                        Dictionary dict = new Dictionary(strID, strEnglish, strKorean);

                        //mArrayList.add(0, dict); //첫 줄에 삽입
                        mArrayList.add(dict); //마지막 줄에 삽입
                        mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영
                        savedList = mArrayList;
                        savedList2 = mArrayList;
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        writeLogFile();
    }

    private void writeLogFile() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor edit = prefs.edit();
        JSONArray array1 = new JSONArray();
        JSONArray array2 = new JSONArray();
        JSONArray array3 = new JSONArray();
        for (int i = 0; i < savedList2.size(); i++) {
            array1.put(savedList2.get(i).getId());
            array2.put(savedList2.get(i).getEnglish());
            array3.put(savedList2.get(i).getKorean());
        }
        edit.putString("id", array1.toString());
        edit.putString("english", array2.toString());
        edit.putString("korean", array3.toString());
        edit.commit();


    }

    private ArrayList<Dictionary> readLogFile() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String json = prefs.getString("id", null);
        String json2 = prefs.getString("english", null);
        String json3 = prefs.getString("korean", null);
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                JSONArray array2 = new JSONArray(json2);
                JSONArray array3 = new JSONArray(json3);
                savedList2 = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    String url = array.optString(i);
                    String url2 = array2.optString(i);
                    String url3 = array3.optString(i);
                    savedList2.add(new Dictionary(url, url2, url3));
//                            savedList2.add(i, null);
//                            savedList2.get(i).setId(url);
//                            savedList2.get(i).setEnglish(url2);
//                            savedList2.get(i).setKorean(url3);
                }
                return savedList2;
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            savedList2 = new ArrayList<>();
            return savedList2;
        }
        return savedList2;
    }
}
