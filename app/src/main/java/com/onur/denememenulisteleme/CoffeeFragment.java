package com.onur.denememenulisteleme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onur.denememenulisteleme.databinding.FragmentCoffeeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoffeeFragment extends Fragment {
    private FragmentCoffeeBinding binding;
    CoffeesAdapter adapter;
    FirebaseFirestore firestore;
    ArrayList<ModelCoffees> coffeeList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCoffeeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        firestore = FirebaseFirestore.getInstance();
        coffeeList = new ArrayList<>();
        adapter = new CoffeesAdapter(getActivity(),coffeeList);
        binding.recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);

        LoadingDataFunc();


        return view;

    }

    private void LoadingDataFunc() {
        coffeeList.clear();
        firestore.collection("coffees").orderBy("id", Query.Direction.ASCENDING).addSnapshotListener((value, error) -> {
           if(error != null)
               Toast.makeText(getActivity(), "document loaded", Toast.LENGTH_SHORT).show();
           for(DocumentChange dc : Objects.requireNonNull(value).getDocumentChanges()){
               if(dc.getType()== DocumentChange.Type.ADDED){
                   coffeeList.add(dc.getDocument().toObject(ModelCoffees.class));
               }
                adapter.notifyDataSetChanged();
           }
        });
    }


}