package com.baptistebr.iem.tdd_gestionfichier;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baptistebr.iem.tdd_gestionfichier.Adapters.NavigationAdapter;
import com.baptistebr.iem.tdd_gestionfichier.Fragments.MediaFragment;
import com.baptistebr.iem.tdd_gestionfichier.Items.NavigationLigne;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private DrawerLayout jdl_navigation;
    private ListView jlv_navigation;
    private ActionBarDrawerToggle abDrawerToggle;
    private ArrayList<NavigationLigne> alNavigationLigne;
    private CharSequence drawerTitre;
    private CharSequence titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaManager.synchroniserDonnees();
        initialiserActivite();
    }

    /**
     * Initialise l'activité
     */
    private void initialiserActivite() {

        titre = drawerTitre = getTitle();

        // Récupère les composants graphiques
        jdl_navigation = (DrawerLayout) findViewById(R.id.dl_navigation);
        jlv_navigation = (ListView) findViewById(R.id.lv_navigation);

        // Initialise la liste des items de navigation
        alNavigationLigne = new ArrayList<NavigationLigne>();
        alNavigationLigne.add(new NavigationLigne(R.drawable.ic_navigation_image, getResources().getString(R.string.navigation_image)));
        alNavigationLigne.add(new NavigationLigne(R.drawable.ic_navigation_texte, getResources().getString(R.string.navigation_texte)));
        alNavigationLigne.add(new NavigationLigne(R.drawable.ic_navigation_son, getResources().getString(R.string.navigation_son)));

        // Création de l'ActionBar de navigation
        abDrawerToggle = new ActionBarDrawerToggle(
                this, jdl_navigation, R.drawable.ic_navigation, R.string.drawer_ouvrir, R.string.drawer_fermer) {

            /**
             * Titre quand le drawer ne contient pas de fragment
             * @param view
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(titre);
            }

            /**
             * Titre quand le drawer contient un fragment
             * @param drawerView
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(drawerTitre);
            }
        };

        jdl_navigation.setDrawerListener(abDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        drawerTitre = getResources().getText(R.string.drawer_ouvrir);

        NavigationAdapter navigationAdapter = new NavigationAdapter(getApplicationContext(), alNavigationLigne);
        jlv_navigation.setAdapter(navigationAdapter);
        jlv_navigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                switch (i) {
                    case 0:
                        fragment = new MediaFragment();
                        ((MediaFragment)fragment).setFragmentParameters(alNavigationLigne.get(0).nom,
                                alNavigationLigne.get(0).icone);
                        break;
                    case 1:
                        fragment = new MediaFragment();
                        ((MediaFragment)fragment).setFragmentParameters(alNavigationLigne.get(1).nom,
                                alNavigationLigne.get(1).icone);
                        break;
                    case 2:
                        fragment = new MediaFragment();
                        ((MediaFragment)fragment).setFragmentParameters(alNavigationLigne.get(2).nom,
                                alNavigationLigne.get(2).icone);
                        break;
                    default:
                        break;

                }
                if(fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fl_main, fragment).commit();
                    jlv_navigation.setItemChecked(i, true);
                    jlv_navigation.setSelection(i);
                    titre = alNavigationLigne.get(i).nom;
                    getActionBar().setTitle(titre);
                    jdl_navigation.closeDrawers();
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        abDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getActionBar().setTitle(getResources().getString(R.string.drawer_fermer));
            return true;
        }

        if (abDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
