package com.miguel.cerlacommobile;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;
import com.miguel.cerlacommobile.databinding.ActivityPrincipalBinding;

public class Principal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPrincipalBinding binding;

    public static String rol_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.appBarPrincipal.toolbar);
        binding.appBarPrincipal.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View headerview = navigationView.getHeaderView(0);
        TextView txt_correo = headerview.findViewById(R.id.header_correo);
        TextView txt_rol = headerview.findViewById(R.id.header_rol);

        FirebaseUser firebaseUser = MainActivity.auth.getCurrentUser();

        if(firebaseUser!= null){

            txt_correo.setText(firebaseUser.getEmail());

            MainActivity.ctlUsuario.obtener_rol(firebaseUser.getUid(),rol -> {
                txt_rol.setText(rol);
                rol_usuario = rol;





            });






        }

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_perfil, R.id.nav_usuarios, R.id.nav_registroc,R.id.nav_cuadrilla, R.id.nav_agendar, R.id.nav_visitast,R.id.nav_ver_visitas, R.id.nav_informacion )
                .setOpenableLayout(drawer)
                .build();




        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}