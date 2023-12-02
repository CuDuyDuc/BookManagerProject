package com.example.bookmanagerproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagerproject.Fragment.TopFragment;
import com.example.bookmanagerproject.Model.Top;
import com.example.bookmanagerproject.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    Context context;
    TopFragment topFragment;
    ArrayList<Top> list;

    TextView txtSach, txtSoLuong;
    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.topFragment = topFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_top, null);
        }

        final Top top = list.get(position);
        if(top != null) {
            txtSach = view.findViewById(R.id.txtSach);
            txtSach.setText("Sách: "+top.getTenSach());

            txtSoLuong = view.findViewById(R.id.txtSoLuong);
            txtSoLuong.setText("Số Lượng: "+top.getSoLuong());
        }
        return view;
    }
}
