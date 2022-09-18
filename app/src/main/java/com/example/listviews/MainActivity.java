package com.example.listviews;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btnShowListView;
    ListView lvCustomLayoutFlowers;
    Flower selectedFlowerInListView;
    Dialog dialog;

    ArrayList<Flower> flowerList;
    FlowerAdapter flowerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowListView=findViewById(R.id.btnShowListView);
        btnShowListView.setOnClickListener(this);
        createArrayList();
    }
    protected void createArrayList()
    {
        //create the objects
        Flower t1 = new Flower(50,"for homes","very nice flower",R.drawable.f1);
        Flower t2 = new Flower(70,"for offices","artistic flower",R.drawable.f2);
        Flower t3 = new Flower(90,"for pictures","one line flower",R.drawable.f3);
        Flower t4 = new Flower(29,"for nature","nature flower",R.drawable.f4);
        Flower t5 = new Flower(37,"for collectors","unique flower",R.drawable.f5);
        Flower t6 = new Flower(50,"for good taste","special flower",R.drawable.f6);
        Flower t7 = new Flower(29,"for view only","protected flower",R.drawable.f7);
        Flower t8 = new Flower(37,"for rich people","expensive flower",R.drawable.f8);
        Flower t9 = new Flower(50,"for all people","cheap flower",R.drawable.f9);

        //phase 2 - add to array list
        flowerList = new ArrayList<>();
        flowerList.add(t1);
        flowerList.add(t2);
        flowerList.add(t3);
        flowerList.add(t4);
        flowerList.add(t5);
        flowerList.add(t6);
        flowerList.add(t7);
        flowerList.add(t8);
        flowerList.add(t9);


    }

    @Override
    public void onClick(View view) {
        if(view==btnShowListView)
        {
            dialog=new Dialog(this);
            dialog.setContentView(R.layout.layout_for_listview);
            dialog.setCancelable(true);

            //phase 3 - create adapter
            flowerAdapter=new FlowerAdapter(this,0,0, flowerList);
            //phase 4 reference to listview
            lvCustomLayoutFlowers=dialog.findViewById(R.id.lvCustomLayoutFlowers);

            lvCustomLayoutFlowers.setAdapter(flowerAdapter);
            lvCustomLayoutFlowers.setOnItemClickListener(this);

            dialog.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectedFlowerInListView = flowerAdapter.getItem(i);

        Intent intent = new Intent(MainActivity.this, EditFlowerActivity.class);
        intent.putExtra("title", selectedFlowerInListView.getTitle());
        intent.putExtra("sub", selectedFlowerInListView.getSubTitle());
        intent.putExtra("price", selectedFlowerInListView.getPrice());
        intent.putExtra("imageId", selectedFlowerInListView.getImageId());
        editFlowerActivtyResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> editFlowerActivtyResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String title = data.getExtras().getString("title");
                        String subtitle = data.getExtras().getString("sub");
                        int price = data.getExtras().getInt("price");
                        int resourceId = data.getExtras().getInt("imageId");

                        selectedFlowerInListView.setPrice(price);
                        selectedFlowerInListView.setTitle(title);
                        selectedFlowerInListView.setSubTitle(subtitle);
                        selectedFlowerInListView.setImageId(resourceId);

                        flowerAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_LONG).show();
                    }
                }
            }
    );

}