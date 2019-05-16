package softocean.app.daggerttt.ui;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import softocean.app.daggerttt.R;
import softocean.app.daggerttt.di.DaggerComponentHolder;
import softocean.app.daggerttt.util.GlideApp;
import softocean.app.daggerttt.vm.MainViewModel;
import softocean.app.daggerttt.vo.City;

public class MainFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @VisibleForTesting
    public MainViewModel viewModel;

    private MyAdapter mAdapter;
    private List<City> mCityList;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_main_fragment, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(viewModel==null){
            viewModel =
                    ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        }

        viewModel.getCityLiveData().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cities) {
                if(cities!=null && cities.size()>0){
                    mCityList.addAll(cities);
                    mAdapter.notifyDataSetChanged();
                }

            }
        });

        viewModel.getLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                mProgressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        });

        viewModel.getErrorMsgLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getCityList();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initViews(View v){
        mProgressBar = v.findViewById(R.id.loading);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter();
        mCityList = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.VISIBLE);

        View button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getCityList();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DaggerComponentHolder.getAppComponent().inject(this);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.view_list_row, parent, false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            City city = mCityList.get(position);
            holder.descView.setText(city.getDescription());
            holder.titleView.setText(city.getName());

            GlideApp.with(getActivity())
                    .load(city.getImg())
                    .fitCenter()
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mCityList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageView;
            public TextView titleView;
            public TextView descView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
                titleView = itemView.findViewById(R.id.text);
                descView = itemView.findViewById(R.id.desc);
            }
        }
    }
}
