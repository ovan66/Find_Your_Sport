package com.bastian.findyousport.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.views.create.SteperActivity;
import com.bastian.findyousport.views.login.FullscreenActivity;
import com.bastian.findyousport.views.main.mySubscriptions.MySubscriptionsListActivity;
import com.bastian.findyousport.views.profile.CreateProfileActivity;
import com.firebase.ui.auth.AuthUI;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(true);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUserData(navigationView);
    }

    private void setUserData(NavigationView navigationView){
        FirebaseUser user = new UserData().user();
        TextView userNickMenu = (TextView) findViewById(R.id.userNick);
        String userName = user.getDisplayName();
        userNickMenu.setText(userName);
        CircularImageView circularImageView = (CircularImageView) findViewById(R.id.userAvatar);
        Picasso.with(this).load(user.getPhotoUrl()).into(circularImageView);
        View header = navigationView.getHeaderView(0);
        CircularImageView drawerAvatar = (CircularImageView) header.findViewById(R.id.userAvatarDrawer);
        Picasso.with(this).load(user.getPhotoUrl()).into(drawerAvatar);
        TextView userNickDrawer = (TextView) header.findViewById(R.id.userName);
        userNickDrawer.setText(userName);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.myPublications) {
            startActivity(new Intent(this, SteperActivity.class));
            //TODO esto debe enviar al usuario a su perfil, que contenga sus publicaciones
        } else if (id == R.id.mySubscriptions) {
            Intent intent = new Intent(MainActivity.this, MySubscriptionsListActivity.class);
            startActivity(intent);

        } else if (id == R.id.logout) {
            logout();
        } else if (id == R.id.profile) {
            startActivity(new Intent(this, CreateProfileActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }
}
