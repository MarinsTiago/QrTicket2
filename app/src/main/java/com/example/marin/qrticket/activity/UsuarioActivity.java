package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.activity.editar.EditarUsuario;
import com.example.marin.qrticket.adapter.EventoAdapter;
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Tela que o usuario é redirecionado e ao qual possui algumas opções
public class UsuarioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REDIRECT = 200;
    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario) getIntent().getSerializableExtra("usuario");
        getSupportActionBar().setTitle("Seja bem vindo "+ user.getNome() + "!");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Configuração para exibir imagem da internet utilizando a biblioteca Image loader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3)
                .diskCacheExtraOptions(480, 320, null)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.editInfoUser) {
            //Pega o objeto usuario que foi mandado de outra tela e armazena o mesmo em um novo usuario
            user = (Usuario) getIntent().getSerializableExtra("usuario");

            //Mostra o caminho que acontecerá o fluxo de informações, primeira tela é a que
            //estamos(UsuarioActivity.class) e a segunda
            //é onde será levada as informações (EditarUsuario.class) e posteriormente renderizada a tela
            Intent intent = new Intent(UsuarioActivity.this, EditarUsuario.class);

            //Passa o objeto usuario para outra activity(tela), como se fosse uma espécie de sessão
            intent.putExtra("usuario", user);

            //Abre a activity
            startActivityForResult(intent, REDIRECT);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ListView listar = (ListView) findViewById(R.id.listarPrincipais);
        RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<List<Evento>> call = retrofitUtil.listarEventos();
        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                final List<Evento> listarEventos = response.body();
                if (listarEventos != null){
                    EventoAdapter adapter = new EventoAdapter(getBaseContext(), listarEventos);
                    listar.setAdapter(adapter);
                    listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(UsuarioActivity.this, TesteActivity.class);
                            int e = listarEventos.get(i).getId();
                            //Passando o id do evento escolhido para a tela "TesteActivity'
                            intent.putExtra("id", e);
                            //pegando o objeto usuario que vem de outra activity
                            user = (Usuario) getIntent().getSerializableExtra("usuario");
                            //enviando o objeto usuario para a activity 'TesteActivity'
                            intent.putExtra("usuario", user);
                            startActivityForResult(intent, REDIRECT);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //ver eventos
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(UsuarioActivity.this, EventoActivity.class);
            user = (Usuario) getIntent().getSerializableExtra("usuario");
            intent.putExtra("usuario", user);
            startActivityForResult(intent, REDIRECT);
        }
        //Visualizar ingressos
        else if (id == R.id.nav_manage) {
            Intent intent = new Intent(UsuarioActivity.this, IngressoUsuarioActivity.class);
            user = (Usuario) getIntent().getSerializableExtra("usuario");
            intent.putExtra("usuario", user);
            startActivityForResult(intent, REDIRECT);
        }
        //compartilhar ingresso
        else if (id == R.id.nav_share) {
            Intent intent = new Intent(UsuarioActivity.this, ActivityShareIngresso.class);
            user = (Usuario) getIntent().getSerializableExtra("usuario");
            intent.putExtra("usuario", user);
            startActivityForResult(intent, REDIRECT);
        }
        //sair
        else if (id == R.id.nav_send) {
            Intent intent = new Intent(UsuarioActivity.this, LoginActivity.class);
            startActivityForResult(intent, REDIRECT);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
