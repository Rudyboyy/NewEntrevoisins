package com.openclassrooms.entrevoisins.ui.neighbour;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.NEIGHBOURS_INFO;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView avatar;
    @BindView(R.id.textView)
    TextView nom1;
    @BindView(R.id.textView2)
    TextView nom2;
    @BindView(R.id.textView3)
    TextView adress;
    @BindView(R.id.textView4)
    TextView phoneNumber;
    @BindView(R.id.textView5)
    TextView socialNetwork;
    @BindView(R.id.textView7)
    TextView aboutMe;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton favoriteButton;
    @BindView(R.id.imageButton)
    ImageButton backButton;

    private NeighbourApiService mApiService;

    Neighbour myNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        myNeighbour = (Neighbour) getIntent().getSerializableExtra(NEIGHBOURS_INFO);
        Glide.with(this).load(myNeighbour.getAvatarUrl()).into(avatar);
        nom1.setText(myNeighbour.getName());
        nom2.setText(myNeighbour.getName());
        adress.setText(myNeighbour.getAddress());
        phoneNumber.setText(myNeighbour.getPhoneNumber());
        aboutMe.setText(myNeighbour.getAboutMe());
        socialNetwork.setText(getString(R.string.facebook_profile_url, myNeighbour.getName().toLowerCase()));

        backButton.setOnClickListener(view -> this.finish());

        favoriteButton.setOnClickListener(view -> {
            mApiService.toggleIsNeighbourFavorite(myNeighbour);
            myNeighbour = mApiService.getNeighbourById(myNeighbour.getId());
            setNeighbourIsFavorite(myNeighbour);
        });
    }

    public void setNeighbourIsFavorite(@NonNull Neighbour myNeighbour) {
        if (myNeighbour.isFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNeighbourIsFavorite(myNeighbour);
    }
}