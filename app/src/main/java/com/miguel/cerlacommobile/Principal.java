package com.miguel.cerlacommobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
        binding.appBarPrincipal.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
        
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View headerview = navigationView.getHeaderView(0);
        TextView txt_correo = headerview.findViewById(R.id.header_correo);
        TextView txt_rol = headerview.findViewById(R.id.header_rol);

        FirebaseUser firebaseUser = MainActivity.auth.getCurrentUser();

        if(firebaseUser!= null){

            txt_correo.setText(firebaseUser.getEmail());

            MainActivity.ctlUsuario.Obtener_rol(firebaseUser.getUid(),rol -> {
                txt_rol.setText(rol);
                rol_usuario = rol;


                if(rol_usuario.equalsIgnoreCase("Administrador")){

                    mAppBarConfiguration = new AppBarConfiguration.Builder(
                            R.id.nav_inicio, R.id.nav_perfil, R.id.nav_usuarios,R.id.nav_cuadrilla, R.id.nav_visitast, R.id.nav_informacion )
                            .setOpenableLayout(drawer)
                            .build();

                }
                if (rol_usuario.equalsIgnoreCase("Operador")){

                    mAppBarConfiguration = new AppBarConfiguration.Builder(
                            R.id.nav_inicio, R.id.nav_perfil, R.id.nav_usuarios,R.id.nav_cuadrilla, R.id.nav_visitast, R.id.nav_informacion )
                            .setOpenableLayout(drawer)
                            .build();


                }
                if (rol_usuario.equalsIgnoreCase("Cuadrilla")){

                    mAppBarConfiguration = new AppBarConfiguration.Builder(
                            R.id.nav_inicio, R.id.nav_perfil, R.id.nav_visitast, R.id.nav_informacion )
                            .setOpenableLayout(drawer)
                            .build();

                    binding.navView.getMenu().findItem(R.id.nav_usuarios).setVisible(false);
                    binding.navView.getMenu().findItem(R.id.nav_registroc).setVisible(false);
                    binding.navView.getMenu().findItem(R.id.nav_cuadrilla).setVisible(false);

                }

                if (rol_usuario.equalsIgnoreCase("Usuario")){

                    mAppBarConfiguration = new AppBarConfiguration.Builder(
                            R.id.nav_inicio, R.id.nav_perfil,  R.id.nav_visitast, R.id.nav_informacion )
                            .setOpenableLayout(drawer)
                            .build();

                    binding.navView.getMenu().findItem(R.id.nav_usuarios).setVisible(false);

                    binding.navView.getMenu().findItem(R.id.nav_cuadrilla).setVisible(false);



                }

                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
                NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
                NavigationUI.setupWithNavController(navigationView, navController);

            });


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {


            MainActivity.auth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));






            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}