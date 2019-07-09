package com.example.farmer_assist;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MenuListAdapter extends BaseAdapter {
    String [] result;
    Context context;
    private static LayoutInflater inflater=null;

    public MenuListAdapter(UserDashboard mainActivity, String[] prgmNameList){
        result=prgmNameList;
        context=mainActivity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder
    {
        TextView tv_menu_title;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.layout_menu_list_item, null);
        holder.tv_menu_title=(TextView) view.findViewById(R.id.tv_menuTitle);

        holder.tv_menu_title.setText(result[i]);
//        holder.im_menu.setImageResource(imageId[i]);
//        Picasso.with(context).load(imageId[i]).into(holder.im_menu);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[i], Toast.LENGTH_LONG).show();
                if(result[i].equals("Weather")){
                    Intent j=new Intent(context, ShowWeatherActivity.class);
                    context.startActivity(j);
                }else if(result[i].equals("About")) {
                    Intent j = new Intent(context, About.class);
                    context.startActivity(j);
                }
                else if(result[i].equals("Products")) {
                    Intent j = new Intent(context, ShowProducts.class);
                    context.startActivity(j);
                }
                else if(result[i].equals("Logout")) {
                    SharedPreferences settings = context.getSharedPreferences("logged_user_pref", Context.MODE_PRIVATE);
                    settings.edit().remove("logged_user_id").commit();
                    Intent j = new Intent(context, MainActivity.class);
                    context.startActivity(j);

                }
            }
        });
        return view;
    }
}
