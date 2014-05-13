package dev.yamacraft.garbagebox.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dev.yamacraft.garbagebox.app.R;

/**
 * Created by wataru.yamada on 2014/04/28.
 */
public class ScribeFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<String> {

    @InjectView(R.id.console_text)
    TextView mConsoleText;

    public ScribeFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scribe, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.oauth_button)
    void callOauth(){

        OauthAsyncTaskLoader loader
                = new OauthAsyncTaskLoader(this.getActivity());
        loader.forceLoad();

        //Bundle args = new Bundle();
        //getLoaderManager().initLoader(0, args, this);
    }

    private OAuthService mService;
    private Token mRequestToken;

    private class OauthAsyncTaskLoader extends AsyncTaskLoader<String> {

        public OauthAsyncTaskLoader(Context context) {
            super(context);
            mService = new ServiceBuilder()
                    .provider(TwitterApi.SSL.class)
                    .apiKey("XXXXXXXXXXXXXXXXXXXXXXXXX")
                    .apiSecret("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                    .callback("callback://dev.yamacraft.garbagebox/oauth")
                    .build();
        }

        @Override
        public String loadInBackground() {
            // OAuthテスト
            mRequestToken = mService.getRequestToken();
            String url = mService.getAuthorizationUrl(mRequestToken);
            Log.d("REQUEST_TOKEN", mRequestToken.toString());
            Log.d("URL", url.toString());
            return url.toString();
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        // 非同期で処理を実行するLoaderを生成します.
        // ここを切り替えてあげるだけで様々な非同期処理に対応できます.
        if(args != null) {
            String url = args.getString("url");
            return new OauthAsyncTaskLoader(this.getActivity());
        }else{
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<String> arg0, String arg1) {
        mConsoleText.setText(arg1);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
