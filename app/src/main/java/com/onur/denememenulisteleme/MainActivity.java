package com.onur.denememenulisteleme;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.onur.denememenulisteleme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        binding.ViewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.ViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Kahveler");
                    break;
                case 1:
                    tab.setText("Doğal İçecekler");
                    break;
                case 2:
                    tab.setText("Tatlı ve Pastalar");
                    break;
                case 3:
                    tab.setText("Kitap ve Dergiler");
                    break;
            }
        }).attach();



        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull MainActivity fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new CoffeeFragment();
                case 1:
                    return new NaturalDrinkFragment();
                case 2:
                    return new DesertAndCakeFragment();
                case 3:
                    return new BooksFragment();
                default:
                    return new CoffeeFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 4; // Number of tabs
        }
    }
}