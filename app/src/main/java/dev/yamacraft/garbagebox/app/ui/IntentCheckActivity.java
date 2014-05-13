package dev.yamacraft.garbagebox.app.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import dev.yamacraft.garbagebox.app.R;

/**
 * Created by wataru.yamada on 2014/05/07.
 */
public class IntentCheckActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentcheck);
        ButterKnife.inject(this);
    }
}
