package com.onur.denememenulisteleme;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CoffeesAdapter extends RecyclerView.Adapter<CoffeesAdapter.ViewHolder> {
    Context context;
    ArrayList<ModelCoffees> Coffeeslist ;


    public CoffeesAdapter(Context context, ArrayList<ModelCoffees> coffeeslist) {
        this.context = context;
        Coffeeslist = coffeeslist;
    }

    @NonNull
    @Override
    public CoffeesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coffees_recycler_view_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CoffeesAdapter.ViewHolder holder, int position) {
        ModelCoffees modelCoffees = Coffeeslist.get(position);
        holder.bindfunc(modelCoffees, context);



    }

    @Override
    public int getItemCount() {
        return Coffeeslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coffeeImage;
        private TextView coffeeName, coffeePrice,sizeSmall, sizeMedium, sizeLarge,extraMilk, extraSyrup, extraEspresso;
        private Button addToCartButton;
        private String selectedSize;
        private List<String> selectedExtras;
        private double currentPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeImage = itemView.findViewById(R.id.imageViewCoffee);
            coffeeName = itemView.findViewById(R.id.textViewCoffeeName);
            coffeePrice = itemView.findViewById(R.id.textViewPrice);
            sizeSmall = itemView.findViewById(R.id.textViewSmall);
            sizeMedium = itemView.findViewById(R.id.textViewMedium);
            sizeLarge = itemView.findViewById(R.id.textViewLarge);
            extraMilk = itemView.findViewById(R.id.textViewExtraMilk);
            extraSyrup = itemView.findViewById(R.id.textViewExtraSyrup);
            extraEspresso = itemView.findViewById(R.id.textViewExtraEspresso);
            addToCartButton = itemView.findViewById(R.id.buttonAddToCart);
            selectedExtras = new ArrayList<>();
        }

        public void bindfunc(ModelCoffees modelCoffees, Context context) {
            coffeeName.setText(modelCoffees.getName());
            coffeePrice.setText(String.valueOf(modelCoffees.getPrice()));
            Glide.with(context)
                    .load(modelCoffees.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(coffeeImage);

            sizeSmall.setOnClickListener(v -> toggleSizeSelection(sizeSmall, "Small", modelCoffees.getPrice()));
            sizeMedium.setOnClickListener(v -> toggleSizeSelection(sizeMedium, "Medium",  modelCoffees.getPrice()));
            sizeLarge.setOnClickListener(v -> toggleSizeSelection(sizeLarge, "Large",  modelCoffees.getPrice()));
            extraMilk.setOnClickListener(v -> toggleExtraSelection(extraMilk, "Milk", 10));
            extraSyrup.setOnClickListener(v -> toggleExtraSelection(extraSyrup, "Syrup", 10));
            extraEspresso.setOnClickListener(v -> toggleExtraSelection(extraEspresso, "Espresso", 10));


            addToCartButton.setOnClickListener(v -> addToCart(modelCoffees, context));


        }
        private void toggleSizeSelection(TextView sizeView, String size, double priceChange) {
            if (selectedSize != null && selectedSize.equals(size)) {
                sizeView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.darker_gray));
                currentPrice -= priceChange;
                selectedSize = null;
            } else {
                resetSizeSelection();
                sizeView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.icogreen));
                if (selectedSize != null) {
                    currentPrice -= getSizePrice(selectedSize);
                }
                currentPrice += priceChange;
                selectedSize = size;
            }
            coffeePrice.setText(String.format(Locale.getDefault(), "%.2f TL", currentPrice));
        }

        private void toggleExtraSelection(TextView extraView, String extraName, double priceChange) {
            if (selectedExtras.contains(extraName)) {
                extraView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.darker_gray));
                selectedExtras.remove(extraName);
                currentPrice -= priceChange;
            } else {
                extraView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.icogreen));
                selectedExtras.add(extraName);
                currentPrice += priceChange;
            }
            coffeePrice.setText(String.format(Locale.getDefault(), "%.2f TL", currentPrice));
        }
        private double getSizePrice(String size) {
            switch (size) {
                case "Small":
                    return 0.0;
                case "Medium":
                    return 10.0;
                case "Large":
                    return 20.0;
                default:
                    return 0.0;
            }
        }

        private void resetSizeSelection() {
            sizeSmall.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.darker_gray));
            sizeMedium.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.darker_gray));
            sizeLarge.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.darker_gray));
        }


        private void addToCart(ModelCoffees coffee, Context context) {
            if (selectedSize != null) {
                // CartManager.addToCart(coffee, selectedSize, selectedExtras);
                Toast.makeText(context, "Ürün sepete eklendi", Toast.LENGTH_SHORT).show();
                coffee.getName();
                coffee.getImageUrl();
                double lastprice = currentPrice;

                // Intent intent = new Intent(context, CartActivity.class);
                // context.startActivity(intent);
            } else {
                Toast.makeText(context, "Lütfen bir boyut seçin", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
