package com.englishlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.englishlearningapp.R;

public class CategoryActivity extends AppCompatActivity {

    private GridView categoryGridView;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryGridView = findViewById(R.id.categoryGridView);
        categories = new ArrayList<>();

        // Add your custom categories with their respective images
        categories.add(new Category("Cooking", R.drawable.plate)); // Using plate.jpg as representative for Cooking
        categories.add(new Category("Furnitures", R.drawable.sofa)); // Using sofa.jpg as representative for Furnitures
        categories.add(new Category("Automobiles", R.drawable.car)); // Using car.jpg as representative for Automotors

        // You can remove or modify the following placeholder categories if they are not needed
        // categories.add(new Category("Animals", R.drawable.ic_menu_gallery));
        // categories.add(new Category("Food", R.drawable.ic_menu_gallery));
        // categories.add(new Category("Nature", R.drawable.ic_menu_gallery));
        // categories.add(new Category("Objects", R.drawable.ic_menu_gallery));
        // categories.add(new Category("Travel", R.drawable.ic_menu_gallery));

        CategoryAdapter adapter = new CategoryAdapter();
        categoryGridView.setAdapter(adapter);
    }

    private class CategoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return categories.size();
        }

        @Override
        public Object getItem(int position) {
            return categories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.grid_item_category, parent, false);
            }

            ImageView categoryIcon = view.findViewById(R.id.categoryIcon);
            TextView categoryName = view.findViewById(R.id.categoryName);

            Category category = categories.get(position);
            categoryIcon.setImageResource(category.getIconResId());
            categoryName.setText(category.getName());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryActivity.this, CategoryDetailActivity.class);
                    intent.putExtra("category", category.getName());
                    startActivity(intent);
                }
            });

            return view;
        }
    }

    private static class Category {
        private String name;
        private int iconResId;

        public Category(String name, int iconResId) {
            this.name = name;
            this.iconResId = iconResId;
        }

        public String getName() {
            return name;
        }

        public int getIconResId() {
            return iconResId;
        }
    }
} 