package com.knight.arch;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.knight.arch.module.HomeModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.RankingFragment;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends InjectableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RankingFragment rankingFragment = new RankingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.id_frame_container, rankingFragment).commit();
    }


    //=============================================================================
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new HomeModule(this));
    }


}
